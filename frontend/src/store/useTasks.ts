import {create} from 'zustand'
import {Task} from "@/interfaces/Task";
import {TaskStatus} from "@/interfaces/TaskStatus";
import dayjs from "dayjs";
import {TaskItem} from "@/interfaces/TaskItem";


// Define the Store type
type Store = {
  _tasks: Map<number, Task>;
  getTasks: () => Task[];
  addTask: (item: Task) => void;
  removeTask: (id: number) => void;
  finishTask: (id: number) => void;
};

// Create the store
export const useTasksStore = create<Store>((set, get) => ({
  _tasks: new Map<number, Task>(),

  getTasks: () => {
    const {_tasks} = get();
    return Array.from(_tasks.values());
  },

  addTask: (task: Task) => {
    set((state) => {
      const newTasks = new Map(state._tasks);

      const taskItem: TaskItem = {
        id: dayjs().unix(),
        startTime: dayjs().format("YYYY-MM-DD HH:mm:ss")
      }
      console.log(task)

      if (task.taskItems){
        task.taskItems.push(taskItem)
      } else {
        task.taskItems = [taskItem]
      }

      newTasks.set(task.id, task);
      return {_tasks: newTasks};
    });
  },

  removeTask: (id: number) => {
    set((state) => {
      const newTasks = new Map(state._tasks);
      newTasks.delete(id);
      return {_tasks: newTasks};
    });
  },

  finishTask: (id: number) => {
    set((state) => {
      const newTasks = new Map(state._tasks);
      const task = newTasks.get(id);
      if (task) {
        task.status = TaskStatus.FINISHED;
        task.taskItems[task.taskItems.length - 1].finishTime = dayjs().format("YYYY-MM-DD HH:mm:ss");
        newTasks.set(id, task);
      }
      return {_tasks: newTasks};
    });
  },
}));