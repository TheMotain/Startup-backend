package fr.iagl.gamification.services.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.iagl.gamification.model.TaskModel;
import fr.iagl.gamification.services.RunnableHashMapService;
import fr.iagl.gamification.utils.ActionDatabase;
import fr.iagl.gamification.utils.TableDatabase;

public class TaskServiceImplTest {

	private TaskServiceImpl akkaTaskServiceImpl;

	@Before
	public void init() {
		this.akkaTaskServiceImpl = new TaskServiceImpl();
	}

	@Test
	public void testTreatTaskWithGoodTableNameAndAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map = Mockito.mock(Map.class);
		Map<ActionDatabase, RunnableHashMapService> map2 = Mockito.mock(Map.class);
		JSONObject json = Mockito.mock(JSONObject.class);
		Mockito.doReturn("point").when(json).getString("table");
		Mockito.doReturn("INSERT").when(json).getString("type");
		TaskModel task = Mockito.mock(TaskModel.class);
		Mockito.doReturn(json).when(task).getNotification();

		Mockito.doReturn(map2).when(map).get(Mockito.any());
		RunnableHashMapService runnable = Mockito.mock(RunnableHashMapService.class);
		Mockito.doReturn(runnable).when(map2).get(Mockito.any());
		Mockito.doReturn(true).when(map).containsKey(Mockito.any());
		Mockito.doReturn(true).when(map2).containsKey(Mockito.any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		Mockito.verify(runnable).runMethod(Mockito.any());
	}

	@Test
	public void testTreatTaskWithGoodTableNameAndBadAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map = Mockito.mock(Map.class);
		Map<ActionDatabase, RunnableHashMapService> map2 = Mockito.mock(Map.class);
		JSONObject json = Mockito.mock(JSONObject.class);
		Mockito.doReturn("point").when(json).getString("table");
		Mockito.doReturn("baaaad").when(json).getString("type");
		TaskModel task = Mockito.mock(TaskModel.class);
		Mockito.doReturn(json).when(task).getNotification();

		Mockito.doReturn(map2).when(map).get(Mockito.any());
		RunnableHashMapService runnable = Mockito.mock(RunnableHashMapService.class);
		Mockito.doReturn(runnable).when(map2).get(Mockito.any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		Mockito.verify(runnable, Mockito.never()).runMethod(Mockito.any());
	}

	@Test
	public void testTreatTaskWithBadTableNameAndGoodAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map = Mockito.mock(Map.class);
		Map<ActionDatabase, RunnableHashMapService> map2 = Mockito.mock(Map.class);
		JSONObject json = Mockito.mock(JSONObject.class);
		Mockito.doReturn("baaaadd").when(json).getString("table");
		Mockito.doReturn("INSERT").when(json).getString("type");
		TaskModel task = Mockito.mock(TaskModel.class);
		Mockito.doReturn(json).when(task).getNotification();

		Mockito.doReturn(map2).when(map).get(Mockito.any());
		RunnableHashMapService runnable = Mockito.mock(RunnableHashMapService.class);
		Mockito.doReturn(runnable).when(map2).get(Mockito.any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		Mockito.verify(runnable, Mockito.never()).runMethod(Mockito.any());
	}

	@Test
	public void testTreatTaskWithBadTableNameAndBadAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map = Mockito.mock(Map.class);
		Map<ActionDatabase, RunnableHashMapService> map2 = Mockito.mock(Map.class);
		JSONObject json = Mockito.mock(JSONObject.class);
		Mockito.doReturn("baaaaad").when(json).getString("table");
		Mockito.doReturn("baaaad").when(json).getString("type");
		TaskModel task = Mockito.mock(TaskModel.class);
		Mockito.doReturn(json).when(task).getNotification();

		Mockito.doReturn(map2).when(map).get(Mockito.any());
		RunnableHashMapService runnable = Mockito.mock(RunnableHashMapService.class);
		Mockito.doReturn(runnable).when(map2).get(Mockito.any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		Mockito.verify(runnable, Mockito.never()).runMethod(Mockito.any());
	}

	@Test
	public void testTreatTaskWithNoneAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map = Mockito.mock(Map.class);
		Map<ActionDatabase, RunnableHashMapService> map2 = Mockito.mock(Map.class);
		JSONObject json = Mockito.mock(JSONObject.class);
		Mockito.doReturn("point").when(json).getString("table");
		Mockito.doReturn("INSERT").when(json).getString("type");
		TaskModel task = Mockito.mock(TaskModel.class);
		Mockito.doReturn(json).when(task).getNotification();

		Mockito.doReturn(map2).when(map).get(Mockito.any());

		Mockito.doReturn(false).when(map2).containsKey(Mockito.any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		Mockito.verify(map, Mockito.never()).get(Mockito.any());
	}

	@Test
	public void testTreatTaskWithNoneActionINSERTNotInMap() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map = Mockito.mock(Map.class);
		Map<ActionDatabase, RunnableHashMapService> map2 = Mockito.mock(Map.class);
		JSONObject json = Mockito.mock(JSONObject.class);
		Mockito.doReturn("point").when(json).getString("table");
		Mockito.doReturn("INSERT").when(json).getString("type");
		TaskModel task = Mockito.mock(TaskModel.class);
		Mockito.doReturn(json).when(task).getNotification();

		Mockito.doReturn(map2).when(map).get(Mockito.any());

		Mockito.doReturn(true).when(map).containsKey(Mockito.any());
		Mockito.doReturn(false).when(map2).containsKey(Mockito.any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		Mockito.verify(map, Mockito.never()).get(Mockito.any());
	}

	@Test
	public void testTreatTaskWithNoneTableMESSAGENotInMap() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map = Mockito.mock(Map.class);
		Map<ActionDatabase, RunnableHashMapService> map2 = Mockito.mock(Map.class);
		JSONObject json = Mockito.mock(JSONObject.class);
		Mockito.doReturn("point").when(json).getString("table");
		Mockito.doReturn("INSERT").when(json).getString("type");
		TaskModel task = Mockito.mock(TaskModel.class);
		Mockito.doReturn(json).when(task).getNotification();

		Mockito.doReturn(map2).when(map).get(Mockito.any());

		Mockito.doReturn(false).when(map).containsKey(Mockito.any());
		Mockito.doReturn(true).when(map2).containsKey(Mockito.any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		Mockito.verify(map, Mockito.never()).get(Mockito.any());
	}

	@Test
	public void testInitMap() throws JSONException {
		akkaTaskServiceImpl.init();

		assertNotNull(akkaTaskServiceImpl.getMap());
	}
}