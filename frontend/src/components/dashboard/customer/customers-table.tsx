'use client';

import * as React from 'react';
import {useEffect, useState} from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import Divider from '@mui/material/Divider';
import Stack from '@mui/material/Stack';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import {useSelection} from '@/hooks/use-selection';
import {Task} from "@/interfaces/Task";
import PlayArrowOutlinedIcon from '@mui/icons-material/PlayArrowOutlined';
import StopOutlinedIcon from '@mui/icons-material/StopOutlined';
import CreateOutlinedIcon from '@mui/icons-material/CreateOutlined';
import DeleteOutlinedIcon from '@mui/icons-material/DeleteOutlined';
import {TaskStatus} from "@/interfaces/TaskStatus";
import {DurationComponent} from "@/components/dashboard/customer/DurationComponent";
import {IconButton} from '@mui/material';
import {useTasksStore} from "@/store/useTasks";

function noop(): void {
  // do nothing
}

interface TasksTableProps {
  count?: number;
  page?: number;
  rows: Task[];
  rowsPerPage?: number;
}
function isPlayable(row: Task) {
  return row.status === TaskStatus.NOTSTARTED || row.status === TaskStatus.FINISHED
}

export function CustomersTable({
  count = 0,
  rows = [],
  page = 0,
  rowsPerPage = 0,
}: TasksTableProps): React.JSX.Element {
  const rowIds = React.useMemo(() => {
    return rows.map((customer) => customer.id);
  }, [rows]);

  const { selectAll, deselectAll, selectOne, deselectOne, selected } = useSelection(rowIds);
  const [duration, setDuration] = useState(0);
  const { finishTask, removeTask } = useTasksStore();

  useEffect(() => {
    duration > 0 && setTimeout(() => setDuration(duration + 1000), 1000);
  }, [duration]);

  return (
    <Card>
      <Box sx={{ overflowX: 'auto' }}>
        <Table sx={{ minWidth: '800px' }}>
          <TableHead>
            <TableRow>
              <TableCell padding="checkbox"></TableCell>
              <TableCell>ID</TableCell>
              <TableCell>Description</TableCell>
              {/*<TableCell>Start Time</TableCell>*/}
              {/*<TableCell>Finish Time</TableCell>*/}
              <TableCell>Duration</TableCell>
              <TableCell>Category</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row: Task) => {
              return (
                <TableRow hover key={row.id} selected={false}>
                  <TableCell padding="checkbox">
                  </TableCell>
                  <TableCell>{row.id}</TableCell>
                  <TableCell>{row.description}</TableCell>
                  {/*<TableCell>{row.startTime ? dayjs(row.startTime).format('MM-DD-YYYY HH:mm') : "-"}</TableCell>*/}
                  {/*<TableCell>{row.finishTime ? dayjs(row.finishTime).format('MM-DD-YYYY HH:mm') : "-"}</TableCell>*/}
                  <TableCell><DurationComponent task={row}/></TableCell>
                  <TableCell>{row.category}</TableCell>
                  <TableCell>{row.status}</TableCell>
                  <TableCell>
                    <Stack direction="row" spacing={1} sx={{ alignItems: "left" }}>
                      { isPlayable(row) && <IconButton><PlayArrowOutlinedIcon color="success"/></IconButton>}
                      {!isPlayable(row) && <IconButton><StopOutlinedIcon color="error" onClick={() => finishTask(row.id)}/></IconButton>}

                      <IconButton>
                        <CreateOutlinedIcon color="info" onClick={() => removeTask(row.id)}/>
                      </IconButton>

                      <IconButton>
                        <DeleteOutlinedIcon color="warning" onClick={() => removeTask(row.id)}/>
                      </IconButton>
                    </Stack>
                  </TableCell>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </Box>
      <Divider />
      <TablePagination
        component="div"
        count={count}
        onPageChange={noop}
        onRowsPerPageChange={noop}
        page={page}
        rowsPerPage={rowsPerPage}
        rowsPerPageOptions={[5, 10, 25]}
      />
    </Card>
  );
}
