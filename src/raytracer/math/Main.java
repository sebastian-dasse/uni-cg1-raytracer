package raytracer.math;

/**
 * This class holds just one main method, which is meant to demonstrate that the classes in the package 
 * <code>raytracer.math</code> meet the acceptance criteria specified in the given task.
 *  
 * @author Sebastian Dassé
 */
public class Main {
	/**
	 * This main method contains the calculations demanded in the task.
	 * 
	 * @param args would hold any arguments that might be passed to this method; however <code>args</code> is not 
	 * 		  being evaluated.
	 */
	public static void main(final String[] args) {
		System.out.println(new Normal3(1, 2, 3).mul(0.5));
		
		System.out.println(new Normal3(1, 2, 3).add(new Normal3(3, 2, 1)));
		
		System.out.println(new Normal3(1, 0, 0).dot(new Vector3(1, 0, 0)));
		System.out.println(new Vector3(1, 0, 0).dot(new Normal3(1, 0, 0)));
		System.out.println(new Vector3(1, 0, 0).dot(new Vector3(1, 0, 0)));
		
		System.out.println(new Point3(1, 1, 1).sub(new Point3(2, 2, 0)));
		
		System.out.println(new Point3(1, 1, 1).sub(new Vector3(4, 3, 2)));
		
		System.out.println(new Point3(1, 1, 1).add(new Vector3(4, 3, 2)));
		
		System.out.println(new Vector3(1, 1, 1).magnitude == Math.sqrt(3));
		
		System.out.println(new Vector3(1, 1, 1).add(new Normal3(4, 3, 2)));
		
		System.out.println(new Vector3(1, 1, 1).add(new Vector3(4, 3, 2)));
		
		System.out.println(new Vector3(1, 1, 1).sub(new Normal3(4, 3, 2)));
		
		System.out.println(new Vector3(-0.707, 0.707, 0).reflectedOn(new Normal3(1, 0, 0)));
		
		System.out.println(new Mat3x3(1, 0, 0, 
				 					  0, 1, 0, 
				 					  0, 0, 1).mul(new Point3(3, 2, 1)));
		
		System.out.println(new Mat3x3(1, 0, 0, 
				 					  0, 1, 0, 
				 					  0, 0, 1).mul(new Vector3(3, 2, 1)));
		
		System.out.println(new Mat3x3(1, 2, 3, 
									  4, 5, 6, 
									  7, 8, 9).mul(new Mat3x3(1, 0, 0, 
												 			  0, 1, 0, 
												 			  0, 0, 1)));
		System.out.println(new Mat3x3(1, 2, 3, 
									  4, 5, 6, 
									  7, 8, 9).mul(new Mat3x3(0, 0, 1, 
											  				  0, 1, 0, 
											  				  1, 0, 0)));
		
		System.out.println();
	}
}
