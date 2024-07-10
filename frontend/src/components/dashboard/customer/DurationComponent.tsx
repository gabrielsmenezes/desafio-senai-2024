import {Task} from "@/interfaces/Task";
import dayjs from "@/lib/dayjs";
import {useEffect, useState} from "react";
import {TaskStatus} from "@/interfaces/TaskStatus";

interface DurationComponentProps {
  task: Task;
}

function calculateDuration(row: Task) {
  let duration = 0

  row.taskItems?.forEach((item) => {
    if (item.startTime && item.finishTime) {
      duration = duration + dayjs.duration(dayjs(item.finishTime).diff(dayjs(item.startTime), "millisecond"))
    }

    else if (item.startTime && !item.finishTime) {
      duration = duration + dayjs.duration(dayjs().diff(dayjs(item.startTime), "millisecond"))
    }
  })

  return dayjs.duration(duration).format("DD:HH:mm:ss")
}

export function DurationComponent({task}: DurationComponentProps) {
  const [duration, setDuration] = useState(calculateDuration(task));

  // Third Attempts
  useEffect(() => {
    if (TaskStatus.FINISHED !== task.status) {
      const timer = setInterval(() => setDuration(calculateDuration(task)), 1000);
      return () => clearInterval(timer);
    }
  }, [duration]);

  return (
      <>
        {duration}
      </>
  );
}