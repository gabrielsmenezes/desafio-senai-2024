import {TaskStatus} from "@/interfaces/TaskStatus";
import {Task} from "@/interfaces/Task";

export function useTasks(): Task[] {
  return [
    {
      id: 1,
      description: "Task 1 - Implement user authentication.",
      startTime: undefined,
      finishTime: undefined,
      category: "Development",
      status: TaskStatus.NOTSTARTED,
    },
    {
      id: 2,
      description: "Task 2 - Design the database schema.",
      startTime: "2023-06-11T09:00:00Z",
      finishTime: undefined,
      category: "Design",
      status: TaskStatus.STARTED
    },
    {
      id: 3,
      description: "Task 3 - Develop the API endpoints.",
      startTime: "2023-06-12T10:00:00Z",
      finishTime: "2023-06-12T14:00:00Z",
      category: "Development",
      status: TaskStatus.FINISHED
    }
  ];
}