import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     *
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {

        int[] auavArr = new int[maxNumberOfAvailableAUAVs];

        ArrayList<Integer> newList = bombWeights;

        newList.sort(Collections.reverseOrder());

        for(int i = 0; i<maxNumberOfAvailableAUAVs; i++){

            int tempCapacity = maxAUAVCapacity;

            for (int j = 0 ; j<newList.size();j++ ) {

                if (newList.get(j) <= tempCapacity && newList.get(j) != 0) {
                    tempCapacity -= newList.get(j);
                    newList.set(j, 0);
                }
            }
            auavArr[i] = tempCapacity; // remaining capacity
        }

        int counter =0; // calculate number of auav's needed
        for (int i : auavArr){
            if (i != 10) counter++;
        }

        if (newList.get(newList.size()-1)!=0 ||newList.get(0)!=0 )
            return -1;
        else return counter;


    }
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}
