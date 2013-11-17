package pl.rafik.geoorganizer.services;

import java.util.List;

import pl.rafik.geoorganizer.model.dto.GeoLocalisation;
import pl.rafik.geoorganizer.model.dto.TaskDTO;

/**
 * Serwis umozliwiajacy manipulowanie na danych zwiazanych z wydarzeniem.
 * 
 * @author rafal.machnik
 * 
 */
public interface ITaskService {

	/**
	 * Metoda dodajaca nowego taska do bazy.
	 * 
	 * @param dto
	 * @return
	 */
	public Long addNewTask(TaskDTO dto);

	/**
	 * Metoda zwraca wszystkie taski z bazy.
	 * 
	 * @return
	 */
	public List<TaskDTO> getAllTasks();

	/**
	 * Metoda zwracajaca taska o podanym ID.
	 * 
	 * @param id
	 * @return
	 */
	public TaskDTO getTask(Long id);

	/**
	 * Metoda pobiera task zwiazany z danym punktem lokalizacyjnym.
	 * 
	 * @param point
	 * @return
	 */
	public List<TaskDTO> getTasks(GeoLocalisation point);

	/**
	 * Metoda pobierajaca taski ktore nalezy wykonac i ich dedline jest czasem
	 * przyszlym.
	 * 
	 * @return
	 */
	public List<TaskDTO> getFutureTasks();

	/**
	 * Pobiera zdania ktorych status oznaczony jest jako NOT DONE.
	 * 
	 * @return
	 */
	public List<TaskDTO> getNotDoneTasks();

	/**
	 * Pobiera zadania wykonane status oznaczony jest jako DONE
	 * 
	 * @return
	 */
	public List<TaskDTO> getDoneTasks();

	/**
	 * Pobiera zadania z przeszla data waznosci.
	 * 
	 * @return
	 */
	public List<TaskDTO> getPastTasks();

	/**
	 * Metoda aktualizujaca rekrod bazy danych.
	 * 
	 * @param dto
	 * @return zwraca liczbe zaktualizowanych zdarzen
	 */
	public int updateTask(TaskDTO dto);

	/**
	 * Metoda usuwajaca dane wydarzenie.
	 * 
	 * @param id
	 * @return zwraca liczbe usunietych wierszy
	 */
	public int deleteTask(Long id);

	/**
	 * Metoda pobiera task zgodny z lokalizacja.
	 * 
	 * @param localisation
	 * @return
	 */
	public TaskDTO getTask(GeoLocalisation localisation);

	/**
	 * Zwraca liste niewykonanych aktualnych zadan.
	 * 
	 * @return
	 */
	public List<TaskDTO> getActualTasks();

	/**
	 * Zmienia status zadania na wykonany.
	 * 
	 * @param id
	 * @return
	 */
	public int makeDone(Long id);

	/**
	 * Zmienia status zdania na niewykonane.
	 * 
	 * @param id
	 * @return
	 */
	public int makeNotDone(Long id);
}