import {TaskStatus} from "@/interfaces/TaskStatus";
import {TaskItem} from "@/interfaces/TaskItem";

export interface Task {
  id: number;
  description: string;
  category: string;
  status: TaskStatus;
  taskItems?: TaskItem[]
}