package fr.iagl.gamification.services.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.iagl.gamification.listener.akka.Task;
import fr.iagl.gamification.services.AkkaTaskService;
import fr.iagl.gamification.services.RunnableHashMap;
import fr.iagl.gamification.utils.ActionDatabase;
import fr.iagl.gamification.utils.TableDatabase;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = AkkaTaskServiceImpl.class)
public class AkkaTaskServiceImplTest {

	@Autowired
	AkkaTaskService akkaTaskServiceImpl;

	@Test
	public void testTreatTaskWithGoodTableNameAndAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map = mock(Map.class);
		Map<ActionDatabase, RunnableHashMap> map2 = mock(Map.class);
		JSONObject json = mock(JSONObject.class);
		doReturn("message").when(json).getString("table");
		doReturn("INSERT").when(json).getString("type");
		Task task = mock(Task.class);
		doReturn(json).when(task).getNotification();

		doReturn(map2).when(map).get(any());
		RunnableHashMap runnable = mock(RunnableHashMap.class);
		doReturn(runnable).when(map2).get(any());
		doReturn(true).when(map).containsKey(any());
		doReturn(true).when(map2).containsKey(any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		verify(runnable).runMethod(any(), any());
	}

	@Test
	public void testTreatTaskWithGoodTableNameAndBadAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map = mock(Map.class);
		Map<ActionDatabase, RunnableHashMap> map2 = mock(Map.class);
		JSONObject json = mock(JSONObject.class);
		doReturn("message").when(json).getString("table");
		doReturn("baaaad").when(json).getString("type");
		Task task = mock(Task.class);
		doReturn(json).when(task).getNotification();

		doReturn(map2).when(map).get(any());
		RunnableHashMap runnable = mock(RunnableHashMap.class);
		doReturn(runnable).when(map2).get(any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		verify(runnable, never()).runMethod(any(), any());
	}

	@Test
	public void testTreatTaskWithBadTableNameAndGoodAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map = mock(Map.class);
		Map<ActionDatabase, RunnableHashMap> map2 = mock(Map.class);
		JSONObject json = mock(JSONObject.class);
		doReturn("baaaadd").when(json).getString("table");
		doReturn("INSERT").when(json).getString("type");
		Task task = mock(Task.class);
		doReturn(json).when(task).getNotification();

		doReturn(map2).when(map).get(any());
		RunnableHashMap runnable = mock(RunnableHashMap.class);
		doReturn(runnable).when(map2).get(any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		verify(runnable, never()).runMethod(any(), any());
	}

	@Test
	public void testTreatTaskWithBadTableNameAndBadAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map = mock(Map.class);
		Map<ActionDatabase, RunnableHashMap> map2 = mock(Map.class);
		JSONObject json = mock(JSONObject.class);
		doReturn("baaaaad").when(json).getString("table");
		doReturn("baaaad").when(json).getString("type");
		Task task = mock(Task.class);
		doReturn(json).when(task).getNotification();

		doReturn(map2).when(map).get(any());
		RunnableHashMap runnable = mock(RunnableHashMap.class);
		doReturn(runnable).when(map2).get(any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		verify(runnable, never()).runMethod(any(), any());
	}

	@Test
	public void testTreatTaskWithNoneAction() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map = mock(Map.class);
		Map<ActionDatabase, RunnableHashMap> map2 = mock(Map.class);
		JSONObject json = mock(JSONObject.class);
		doReturn("message").when(json).getString("table");
		doReturn("INSERT").when(json).getString("type");
		Task task = mock(Task.class);
		doReturn(json).when(task).getNotification();

		doReturn(map2).when(map).get(any());

		doReturn(false).when(map2).containsKey(any());

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		verify(map, never()).get(any());
	}

	@Test
	public void testTreatTaskWithNoneActionINSERTNotInMap() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map = mock(Map.class);
		Map<ActionDatabase, RunnableHashMap> map2 = mock(Map.class);
		JSONObject json = mock(JSONObject.class);
		doReturn("message").when(json).getString("table");
		doReturn("INSERT").when(json).getString("type");
		Task task = mock(Task.class);
		doReturn(json).when(task).getNotification();

		doReturn(map2).when(map).get(any());

		doReturn(true).when(map).containsKey("MESSAGE");
		doReturn(false).when(map2).containsKey("INSERT");

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		verify(map, never()).get(any());
	}

	@Test
	public void testTreatTaskWithNoneTableMESSAGENotInMap() throws JSONException {
		Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map = mock(Map.class);
		Map<ActionDatabase, RunnableHashMap> map2 = mock(Map.class);
		JSONObject json = mock(JSONObject.class);
		doReturn("message").when(json).getString("table");
		doReturn("INSERT").when(json).getString("type");
		Task task = mock(Task.class);
		doReturn(json).when(task).getNotification();

		doReturn(map2).when(map).get(any());

		doReturn(false).when(map).containsKey("MESSAGE");
		doReturn(true).when(map2).containsKey("INSERT");

		akkaTaskServiceImpl.setMap(map);
		akkaTaskServiceImpl.treatTask(task);

		verify(map, never()).get(any());
	}
}
