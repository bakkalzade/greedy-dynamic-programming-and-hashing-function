import java.util.ArrayList;

/**
 * This class accomplishes Mission Nuke'm
 */
public class DefenseAgainstEnemyTroops {
    private ArrayList<Integer> numberOfEnemiesArrivingPerHour;

    public DefenseAgainstEnemyTroops(ArrayList<Integer> numberOfEnemiesArrivingPerHour){
        this.numberOfEnemiesArrivingPerHour = numberOfEnemiesArrivingPerHour;
    }

    public ArrayList<Integer> getNumberOfEnemiesArrivingPerHour() {
        return numberOfEnemiesArrivingPerHour;
    }

    private int getRechargedWeaponPower(int hoursCharging){
        return hoursCharging*hoursCharging;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalEnemyDefenseSolution
     */
    public OptimalEnemyDefenseSolution getOptimalDefenseSolutionDP(){
        int N = numberOfEnemiesArrivingPerHour.size() + 1;
        int[] sol = new int[N];
        sol[0] = 0;


        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();



        for (int i = 0; i < N; i++) {
            hours.add(new ArrayList<>());
        }

        int tempMax;

        for (int j = 1; j < N; j++) {

            int max = 0;
            int maxIndex = 0;
            for (int i = 0; i < j; i++) {
                tempMax = sol[i] + Math.min(getNumberOfEnemiesArrivingPerHour().get(j - 1), getRechargedWeaponPower(j - i));
                if (tempMax > max) {
                    max = tempMax;
                    sol[j] = max;
                    maxIndex = i;
                }
            }
            for (int i: hours.get(maxIndex)){

                hours.get(j).add(i);
            }
            hours.get(j).add(j);
        }

        int maxNumber = 0;
        int max = 0;
        for (int i = 0; i < sol.length; i++) {
            tempMax = sol[i];
            if (tempMax >= maxNumber) {
                max = i;
                maxNumber = tempMax;
            }
        }

        return new OptimalEnemyDefenseSolution(maxNumber, hours.get(max));


/*
        int N = numberOfEnemiesArrivingPerHour.size() + 1;
        int[] sol = new int[N];
        sol[0] = 0;

        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            hours.add(new ArrayList<>());
        }

        int tempMax;


        for (int j = 0; j<sol.length; j++){

            int max = 0;
            int maxIndex = 0;
            for (int i= 0 ; i< j ; i++){
                tempMax = sol[i]+Math.min(getNumberOfEnemiesArrivingPerHour().get(j - 1), getRechargedWeaponPower(j - i));
                if(tempMax>max){
                    max = tempMax;
                    sol[j] = max;
                    maxIndex = i;
                }
            }
            for (int i: hours.get(maxIndex)){
                System.out.println(i);

                hours.get(j).add(i);
            }
            hours.get(j).add(j);
        }
        int maxNumber = 0;
        int max = 0;
        for (int i = 0; i < sol.length; i++) {
            tempMax = sol[i];
            if (tempMax >= maxNumber) {
                max = i;
                maxNumber = tempMax;
            }
        }
        return new OptimalEnemyDefenseSolution(maxNumber, hours.get(max));
*/

        /*
        int N = numberOfEnemiesArrivingPerHour.size() + 1;
        int[] sol = new int[N];
        sol[0] = 0;


        ArrayList<ArrayList<Integer>> hours = new ArrayList<>();



        for (int i = 0; i < N; i++) {
            hours.add(new ArrayList<>());
        }

        int k;
        ArrayList<Integer> K;

        for (int j = 1; j < N; j++) {

            int max = 0;
            int max2 = 0;
            for (int i = 0; i < j; i++) {
                k = sol[i] + Math.min(getNumberOfEnemiesArrivingPerHour().get(j - 1), getRechargedWeaponPower(j - i));
                if (k > max) {
                    max = k;
                    sol[j] = max;
                    max2 = i;
                }
            }
            K = new ArrayList<>(hours.get(max2));
            K.add(j);
            hours.set(j, K);
        }
        int maxNumber = 0;
        int max = 0;
        for (int i = 0; i < sol.length; i++) {
            k = sol[i];
            if (k >= maxNumber) {
                max = i + 1;
                maxNumber = k;
            }
        }
        max--;

        OptimalEnemyDefenseSolution rata = new OptimalEnemyDefenseSolution(maxNumber, hours.get(max));

        return rata;*/

    }

}
