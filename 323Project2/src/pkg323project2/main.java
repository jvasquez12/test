import java.util.*;

public class main {

    public static void main(String[] args) 
    {
        System.out.println("Hello user welcome to the mss program.");
        int choice;
        do
        {
            System.out.println("Enter what you would like to do.");
            System.out.println("\t1. Input an array and find the mss using all 4 algorithms");
            System.out.println("\t2. Input a length and find the mss aof a randomly generated array with that length.");
            System.out.println("\t3. Quit");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            switch(choice)
            {
                case(1):
                    enterArray();
                    break;
                case(2):
                    randomArray();
                    break;
                default:
                    break;
            }
        }while(choice != 3);
    }
    public static void enterArray()
    {
        System.out.println("Enter your array with every number seperated by a comma"
                + "\nlike so: '1,2,3,4,5' and so on. Press enter when done.");
        Scanner in = new Scanner(System.in);
        String array = in.next();
        String[] numbers = array.split(",");
        int length = numbers.length;
        int[] a = new int[length];
        for(int i = 0; i<length; i++)
        {
            a[i] = Integer.parseInt(numbers[i]);
        }
        System.out.println("According to the freshman algorithm, the mss is: " + mss_freshman(a));
        System.out.println("According to the sophmore algorithm, the mss is: " + mss_sophmore(a));
        System.out.println("According to the junior algorithm, the mss is: " + mss_junior(a,0,length-1));
        System.out.println("According to the freshman algorithm, the mss is: " + mss_senior(a));

    }
    public static void randomArray()
    {
        System.out.println("Enter the length of the array you want.");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println("Enter all the numbers of the algorithms you want to run:"
                + "\n1 for freshman, 2 for sophmore, 3 for junior, and 4 for senior."
                + "\n if you want to run all of them enter 1234, for just freshman and senior"
                + "\n enter 14, and so on.");
        String alg = in.next();
        Random rand = new Random();
        int[] a = new int[n];
        for(int i = 0; i<n; i++)
        {
            a[i] = rand.nextInt(101)-50;
        }
        if(alg.contains("1"))
        {
            double end;
            long start = System.currentTimeMillis();
            int mss = mss_freshman(a);
            end = (double) (System.currentTimeMillis() - start)/1000;
            System.out.println("For the freshman algorithm the mss is: " + mss + 
                    " and it took " + end + "s");
        }
        if(alg.contains("2"))
        {
            double end;
            long start = System.currentTimeMillis();
            int mss = mss_sophmore(a);
            end = (double) (System.currentTimeMillis() - start)/1000;
            System.out.println("For the sophmore algorithm the mss is: " + mss + 
                    " and it took " + end + "s");
        }
        if(alg.contains("3"))
        {
            double end;
            long start = System.currentTimeMillis();
            int mss = mss_junior(a,0,n-1);
            end = (double) (System.currentTimeMillis() - start)/1000;
            System.out.println("For the junior algorithm the mss is: " + mss + 
                    " and it took " + end + "s");
        }
        if(alg.contains("4"))
        {
            double end;
            long start = System.currentTimeMillis();
            int mss = mss_senior(a);
            end = (double) (System.currentTimeMillis() - start)/1000;
            System.out.println("For the senior algorithm the mss is: " + mss + 
                    " and it took " + end + "s");
        }
    }
    public static int mss_freshman(int[] a)
    {
        int length = a.length;
        int max_sum = 0;
        for(int i =0; i<length; i++)
        {
            for( int j = i; j < length; j++)
            {
                int this_sum  = 0;
                for (int k = i; k< j; k++)
                {
                    this_sum += a[k];
                }
                if(this_sum > max_sum)
                {
                    max_sum = this_sum;
                }
            }
        }
        return max_sum;
    }
    public static int mss_sophmore(int[] a)
    {
        int length = a.length;
        int max_sum = 0;
        for(int i = 0; i< length; i++)
        {
            int this_sum = 0;
            for(int j = i; j < length; j++)
            {
                this_sum += a[j];
                if(this_sum >max_sum)
                {
                    max_sum = this_sum;
                }
            }
        }
        return max_sum;
    } 
    public static int mss_senior(int[] a)
    {
        int length = a.length;
        int max_sum = 0;
        int this_sum = 0;
        for(int i = 0; i<length; i++)
        {
            this_sum += a[i];
            if(this_sum > max_sum)
            {
                max_sum = this_sum;
            }
            else if(this_sum < 0)
            {
                this_sum = 0;
            }
        }
        return max_sum;
    }
    public static int mss_junior(int[] a, int left, int right)
    {
        if(right == left)
        {
            return a[left];
        }
        if(right == left+1)
        {
            return Math.max(Math.max(a[left], a[right]), (a[left]+a[right]));
        }
        int mid = (left+right)/2;
        int mss_left = mss_junior(a,left,mid);
        int mss_right = mss_junior(a,mid+1,right);
        int mss_mid = mss_junior_middle(a,left,mid,right);
        return Math.max(Math.max(mss_left,mss_right),mss_mid);
    }
    public static int mss_junior_middle(int[] a, int left, int mid, int right)
    {
        int max_sum_left = 0;
        int max_sum_right = 0;
        int this_sum = 0;
        for(int i = mid; i>=left; i--)
        {
            this_sum += a[i];
            if(this_sum > max_sum_left)//-2,11,-4,13,-5,-2
            {
                max_sum_left = this_sum;
            }
        }
        this_sum = 0;
        for(int i = mid+1; i<= right; i++)
        {
            this_sum += a[i];
            if(this_sum > max_sum_right)
            {
                max_sum_right = this_sum;
            }
        }
        
        return max_sum_left+max_sum_right;
    }
}
