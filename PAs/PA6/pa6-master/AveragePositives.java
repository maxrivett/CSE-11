class AveragePositives {
    public static void main(String[] args) {
        double[] d = new double[args.length];
        double sum = 0;
        double avg = 0;
        int ctr = 0;
        for(String s : args) {
            double num = Double.parseDouble(s);
            if (num > 0) {
                sum += num;
                ctr++;
            }
        }
        avg = sum / ctr;
        if (ctr > 0) {
            System.out.println(avg);
        } else {
            System.out.println(0);
        }
    }
}