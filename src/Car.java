public class Car implements Comparable {


    String Make;
    String Model;
    String year;
    String miles;
    String price;

    public Car(String Make,String Model,String year,String miles,String price){

        this.Make = Make;
        this.Model = Model;
        this.year = year;
        this.miles = miles;
        this.price = price;

    }

    public static void main(String[] args) {

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
