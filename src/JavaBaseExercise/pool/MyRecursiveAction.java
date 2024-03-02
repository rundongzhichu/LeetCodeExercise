package JavaBaseExercise.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

    private long workLoad = 0;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {

        //如果工作超出阈值，将任务分解成更小的任务
        if(this.workLoad > 10) {
            System.out.println("将工作负载 : " + this.workLoad);
            //将工作负载分成多个子任务
            List<MyRecursiveAction> subtasks =
                    new ArrayList<MyRecursiveAction>();
            subtasks.addAll(createSubtasks());
            //将子任务加入到任务队列中
            for(RecursiveAction subtask : subtasks){
                subtask.fork();
            }
        } else {
            System.out.println("自己完成工作量: " + this.workLoad);
        }
    }
    //将工作负载分成多个子任务
    private List<MyRecursiveAction> createSubtasks() {
        List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>();
        //将工作负载分成两个子任务   24/2=12  12/2=6
        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
        forkJoinPool.invoke(myRecursiveAction);

    }

}
