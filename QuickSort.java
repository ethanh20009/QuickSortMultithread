import java.util.ArrayList;


public class QuickSort implements Runnable{

    private ArrayList<Integer> arr;
    

    public QuickSort(ArrayList<Integer> arr)
    {
        this.arr = arr;
    }

    @Override
    public void run() {
        if (arr.size() == 1)
        {
            return;
        }
        if (arr.size() == 0)
        {
            return;
        }

        //Arr needs to be split
        int mid = arr.size()/2;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++)
        {
            if (i == mid){continue;}
            if (this.arr.get(i) < this.arr.get(mid))
            {
                left.add(this.arr.get(i));
            }
            else{
                right.add(this.arr.get(i));
            }
        }

        QuickSort leftSort = new QuickSort(left);
        QuickSort rightSort = new QuickSort(right);
        Thread leftThread = new Thread(leftSort);
        Thread rightThread = new Thread(rightSort);

        leftThread.start();
        rightThread.start();

        try{
            leftThread.join();
            rightThread.join();

        }catch(InterruptedException e)
        {
            System.out.println("Interrupted");
        }
        leftSort.getArr().add(this.arr.get(mid));
        leftSort.getArr().addAll(rightSort.getArr());
        this.arr = leftSort.getArr();
    }

    public ArrayList<Integer> getArr() {
        return arr;
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> i = new ArrayList<>();
        i.add(1);
        i.add(2);
        i.add(3);
        i.add(4);
        i.add(5);
        i.add(6);
        i.add(7);
        i.add(8);
        i.add(9);
        i.add(10);

        QuickSort sort = new QuickSort(i);
        Thread t = new Thread(sort);
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            //Ignore
        }
        System.out.println(sort.getArr().toString());
    }
    
}