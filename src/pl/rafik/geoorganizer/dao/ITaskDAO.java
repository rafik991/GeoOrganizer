package pl.rafik.geoorganizer.dao;

import java.util.List;

import com.dropbox.sync.android.DbxException;
import pl.rafik.geoorganizer.model.dto.GeoLocalisation;
import pl.rafik.geoorganizer.model.entity.TaskEntity;

public interface ITaskDAO {

	/**
	 * Metoda dodaje task.
	 * 
	 *
     * @param task
     * @return
	 */
	public String addTask(TaskEntity task) throws DbxException;

	/**
	 * Metoda zwraca wszystkie taski z bazy.
	 * 
	 * @return
	 */
	public List<TaskEntity> getAllTasks() throws DbxException;

	/**
	 * Metoda zwraca task o podanym identyfikatorze.
	 * 
	 * @param id
	 * @return
	 */
	public TaskEntity getTask(String id) throws DbxException;

	/**
	 * Metoda pobiera liste taskow zwiazanych z dana lokalizacja.
	 * 
	 * @param localisation
	 * @return
	 */
	public List<TaskEntity> getTasks(GeoLocalisation localisation) throws DbxException;

	/**
	 * Metoda pobiera liste zadan do wykonania ktorych dedline jest przyszly.
	 * 
	 * @return
	 */
	public List<TaskEntity> getFutureTasks() throws DbxException;

	/**
	 * Metoda pobiera przeszle zadania gdzie dedline ich ulegl juz
	 * przedawnieniu.
	 * 
	 * @return
	 */
	public List<TaskEntity> getPastTasks() throws DbxException;

	/**
	 * Metoda pobiera taski ktore nie zostalywykonane.
	 * 
	 * @return
	 */
	public List<TaskEntity> getNotDoneTasks() throws DbxException;

	/**
	 * Metoda pobiera wykonane zadania.
	 * 
	 * @return
	 */
	public List<TaskEntity> getDoneTasks() throws DbxException;

	/**
	 * Metoda aktualizujaca wydarzenie.
	 * 
	 * @param ent
	 * @return
	 */
	public int updateTask(TaskEntity ent) throws DbxException;

	/**
	 * Metoda usuwajaca rekord bazy.
	 * 
	 * @param id
	 * @return
	 */
	public int deleteTask(String id);

	/**
	 * Metoda pobiera task zgodny z lokalizacja.
	 * 
	 * @param localisation
	 * @return
	 */
	public TaskEntity getTask(GeoLocalisation localisation) throws DbxException;

	/**
	 * Zwraca liste aktualnych zdan, not done i future.
	 * 
	 * @return
	 */
	public List<TaskEntity> getActualTasks() throws DbxException;

	/**
	 * Zmienia status zadania na wykonany.
	 * 
	 * @param id
	 * @return
	 */
	public int makeDone(String id) throws DbxException;

	/**
	 * Zmienia status zadania na nie wykonane.
	 * 
	 * @param id
	 * @return
	 */
	public int makeNotDone(String id) throws DbxException;
}
