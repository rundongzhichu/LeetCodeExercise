package JavaBaseExercise.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

//配置RecursiveTask，返回值为Long
public class MyRecursiveTask  extends RecursiveTask<Long> {

    private long workLoad = 0;

    public MyRecursiveTask (long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected Long compute() {

        //如果工作超出阈值，将任务分解成更小的任务
        if(this.workLoad > 10) {
            System.out.println("将工作负载 : " + this.workLoad);
            //将工作负载分成多个子任务
            List<MyRecursiveTask > subtasks = new ArrayList<MyRecursiveTask >();
            subtasks.addAll(createSubtasks());
            //将子任务加入到任务队列中
            for(RecursiveTask subtask : subtasks){
                subtask.fork();
            }
            //等待子任务执行完，并得到其结果,并将结果相加
            long result = 0;
            for(MyRecursiveTask subtask : subtasks) {
                result += subtask.join();
            }
            return result;
        } else {
            System.out.println("自己完成工作量: " + this.workLoad);
            return 1L ;//返回计算结果
        }
    }
    //将工作负载分成多个子任务
    private List<MyRecursiveTask > createSubtasks() {
        List<MyRecursiveTask > subtasks = new ArrayList<MyRecursiveTask >();
        //将工作负载分成两个子任务   24/2=12  12/2=6
        MyRecursiveTask  subtask1 = new MyRecursiveTask (this.workLoad / 2);
        MyRecursiveTask  subtask2 = new MyRecursiveTask (this.workLoad / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MyRecursiveTask myRecursiveAction = new MyRecursiveTask(24);
        Long invoke = forkJoinPool.invoke(myRecursiveAction);
        System.out.println("最终结果: " + invoke);//4 从结果可以看出，任务被分成了4个子任务，每个子任务都是一个线程

    }

}
