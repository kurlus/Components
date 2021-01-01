package asim.hackertest.dbfileloader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import asim.hackertest.entities.Dish;

public class DishesMockData 
{
	List<Dish> dishesMenu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),			
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("fench fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmaon", false, 450, Dish.Type.FISH)
			);
	
	public static void main(String[] args) throws Exception
	{
		DishesMockData mockData = new DishesMockData();
		mockData.mostCaloriesDishNameByType();
	}

	public void dishesListByType() throws Exception
	{
		// show all dishes by dish type
		Map<Dish.Type, List<Dish>> typeWiseDishes = dishesMenu.stream().collect(Collectors.groupingBy(Dish::getType));
		//typeWiseDishes.keySet().forEach(key -> typeWiseDishes.get(key).toString());
		
		for (Map.Entry<Dish.Type, List<Dish>> entry : typeWiseDishes.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
	}
	
	// get the dish by most calories.
	public void mostCaloriesDishNameByType() throws Exception{
		
		Comparator<Dish> calories =  (d1, d2) -> d1.getCalories() - d2.getCalories();
		Map<Dish.Type, Optional<Dish>> mostCaloriesDish = dishesMenu.stream().collect(
				Collectors.groupingBy(Dish::getType, Collectors.maxBy(calories)));
		
		for (Map.Entry<Dish.Type, Optional<Dish>> entry : mostCaloriesDish.entrySet()) {
		    System.out.println(entry.getKey() + ":" + entry.getValue().toString());
		}
	}
	
	
	
}



