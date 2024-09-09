public class UnitsCharges {
    public static double toatlBill(String usageType, String units){
        double total = 0.0;
        int unitsUsed = Integer.parseInt(units);

        if(usageType.equals("Household")){
            if(unitsUsed<200) {
                total = unitsUsed * 1.5;
            }
            else{
                total = (200 * 1.5) + ((unitsUsed - 200) * 2.0);
            }
        }

        else{
            if(unitsUsed < 500) {
                total = unitsUsed * 2.5;
            }
            else{
                total = (500 * 2.5) + ((unitsUsed - 500) *3.0);
            }
        }
        return total;
    }
}
