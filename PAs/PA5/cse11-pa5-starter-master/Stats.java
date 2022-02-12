class Stats{
    public static void main(String[] args) {
        double[] arr = new double[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            arr[i-1] = Double.parseDouble(args[i]);
        }
        String str = args[0];
        if (str.equals("--product")) {
            double p = 1;
            for (int i = 0; i < arr.length; i++) {
                p *= arr[i];
            }
            System.out.println(p);
        } else if (str.equals("--mean")) {
            double sum = 0;
            int ctr = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                ctr++;
            }
            System.out.println(sum/ctr);
        } else if (str.equals("--total")) {
            double sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            System.out.println(sum);
        } else if (str.equals("--max")) {
            double max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {max = arr[i];}
            }
            System.out.println(max);
        } else if (str.equals("--min")) {
            double min = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < min) {min = arr[i];}
            }
            System.out.println(min);
        } else {
            System.out.println("Bad option " + str);
        }
    }
}