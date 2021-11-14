package com.weiwei.task;

import com.weiwei.common.Messages;
import com.weiwei.exception.BusinessException;

import java.util.*;


public class TaskList {

    ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList() {

    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);

    }


    public void printList() {
        if (taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + this.taskList.get(i));
            }
        } else {
            System.out.println("Empty List!!!");
        }

    }

    //@Override
    public void markedAsDone(int taskNo) throws BusinessException {
        if (!contains(taskList.get(taskNo))) {
            throw new BusinessException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }
        Task task = this.taskList.get(taskNo);
        if (task.isDone) {
            throw new BusinessException(Messages.MESSAGE_TASK_MARK_DONE_FAILED);
        }
        task.markedAsDone();
    }

    public void delTask(int taskNo) throws BusinessException {
        if (contains(taskList.get(taskNo))) {
            taskList.remove(taskNo);
        } else {
            throw new BusinessException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }

    }

    public int getSize() {
        return taskList.size();
    }

    public void clear() {
        taskList.clear();
    }

    public boolean contains(Task toCheck) {
        for (Task task : taskList) {
            if (task.isSameTask(toCheck)) {
                return true;
            }
        }
        return false;
    }

    public Task getTask(int taskNo) throws BusinessException {
        if (contains(taskList.get(taskNo))) {
            return taskList.get(taskNo);
        } else {
            throw new BusinessException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }
    }

    /**
     * validate the task index in java list (not task number display in ui)
     * @param taskNo
     * @throws BusinessException
     */
    public void validateTaskIndex(int taskNo) throws BusinessException {
        if (taskList.size() == 0) {
            throw new BusinessException(Messages.MESSAGE_EMPTY_TASKLIST);
        }
        if (taskNo >= taskList.size() || taskNo < 0) {
            throw new BusinessException(Messages.MESSAGE_TASK_FOUND_FAILED);
        }
    }

    /**
     * fuzzy search: qualified if any keyword appears in a task
     */
    public List<Task> getTaskContainAnyKeyword(Set<String> keywords) {
        final List<Task> matchTasks = new ArrayList<>();
        for (Task task : taskList) {
            final Set<String> wordsInTask = new HashSet<>(task.getWordsInTask());
            if (!Collections.disjoint(wordsInTask, keywords)) {
                matchTasks.add(task);
            }
        }

        return matchTasks;
    }

    /**
     * strict search: qualified only if all keywords appear in a task
     */
    public List<Task> getTaskContainAllKeyword(Set<String> keywords) {
        final List<Task> matchTasks = new ArrayList<>();

        Set<String> wordsInTask;
        for (Task task : taskList) {
            wordsInTask = new HashSet<>(task.getWordsInTask());
            boolean check = true;
            for (String word : keywords) {
                if (!wordsInTask.contains(word)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                matchTasks.add(task);
            }

        }
        return matchTasks;
    }


}
