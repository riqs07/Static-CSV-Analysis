public class Car implements Comparable {


    String Make;
    String Model;
    String year;
    String miles;


    public Car(String Make,String Model,String year,String miles){

        this.Make = Make;
        this.Model = Model;
        this.year = year;
        this.miles = miles;

    }

    public static void main(String[] args) {
        Car betsy = new Car("Honda","Accord","2003","230000");
        System.out.println(betsy);
    }


    @Override
    public String toString(){
        return this.Make + " " + this.Model + " " + this.year + " " + this.miles;
    }


    @Override
    public int compareTo(Object o) {

        /// comparing by abc of name
        Car currentItem = (Car) o;

        int diff = this.toString().compareTo(currentItem.toString());
       return diff ;


    }
}
