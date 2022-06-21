/**
* @author FLEJOU Matéo and JOMAA Naël 
*/
class BiggestSum {
	
	long cnt;  //counter
	
	void principal() {
		testBiggestSum1();
		testBiggestSum2();
		testBiggestSum3();
		testBiggestSum4();
		
		//EffectivenessTestBiggestSum1();
		//EffectivenessTestBiggestSum2();
		//EffectivenessTestBiggestSum3();
		EffectivenessTestBiggestSum4();
	}
	/** Method BiggestSum1 
	 * Determine the subsequence whose sum of integers is maximal, in an array.
	 * (Very slow)
	 * @param tab - The array used by the method.
	 * @param nbElem - The number of elements in the array.
	 * @return result - Table containing the indexes of the subsequence and the biggest sum.
	 */
	int [] biggestSum1 (int [] tab, int nbElem) { 						// Effectiveness = 1 + 3 + 2 2
		int [] result = new int[3];
		if ( tab != null && nbElem <= tab.length ) { 
			int sumMax = 0;
			int sum = 0;
			
			for(int i = 0; i < nbElem; i++){
				
				for(int j = i; j < nbElem; j++){
					sum = 0;
					
					for(int k = i; k <= j; k++){						// K = 
						sum = sum + tab[k];
						cnt++;
						if ( sum == sumMax ) {
							if ( j-i < result[1]-result[0]) {
								result[0] = i; result[1] = j;	// i = index1 and j = index2
							}
						}
						if(sum > sumMax ){
							sumMax = sum;
							result[2] = sumMax;
							result[0] = i; result[1] = j; 		// i = index1 and j = index2
						}
					}
				}
			}
		} else {
			System.err.println ("ERROR : incorrectly set up"); 
			result[0] = -1; result[1] = -1; //Reporting the error
		}
		if ( result[0] == 0 && result[1] == 0 && result[2] == 0 ) {
			result = new int[0];
		}
		return result;
	}
	/**
	* Test method for the method BiggestSum1
	*/
	void testBiggestSum1 (){
	System.out.println("**TEST biggestSum1 ()**"); 
		int[] tab1  = {1,2,4,5,7,10}; // Max Sum = 29 | an index pair (0,5)
		int[] tab2  = {400,-25 ,-12 ,-100}; // Max Sum = 400 | an index pair (0,0)
		int[] tab3  = {-3,6,27,-12,4,-23,2,18}; // Max Sum = 33 | an index pair (1,2)
		int[] tab4  = {1,1,1,1,2,-6,3,3}; // Max Sum = 6 | an index pair (6,7) 
		int[] tab5  = {1,2,4,5,7}; // Max Sum = 19 | an index pair (0,4)
		//borderline case
		int[] tab7  = {-1,-2,-5,-37,-24}; // Negative table
		int[] tab6  = {2000};
		
		testCaseBiggestSum1(tab1,tab1.length,0,5,29);
		testCaseBiggestSum1(tab2,tab2.length,0,0,400);
		testCaseBiggestSum1(tab3,tab3.length,1,2,33);
		testCaseBiggestSum1(tab4,tab4.length,6,7,6);
		testCaseBiggestSum1(tab5,tab5.length,0,4,19);
		testCaseBiggestSum1(tab6,tab6.length,0,0,2000);
		testCaseBiggestSum1(tab7,tab7.length,0,0,0);
		System.out.println("**END TEST biggestSum1 ()**"); 
	}
	/**
	* Test a request to the method BiggestSum1
	* @param tab - The array used by the method.
	* @param nbElem - The number of elements in the array.
	* @param ind1 - index for automatic verification
	* @param ind2 - index for automatic verification
	* @param sumMax - biggest sum for automatic verification
	*/
	void testCaseBiggestSum1 (int [] tab, int nbElem, int ind1, int ind2, int sumMax) {
		System.out.println("table in settings");
		displayTab(tab,nbElem);
		int result [] = biggestSum1(tab,nbElem);
		if ( result.length != 0) {
			System.out.println
			("Expected result : ind1 = " + ind1 + " | ind2 = " + ind2 + " | sumMax = " + sumMax);
			System.out.println
			("Result : ind1 = " + result[0] + " | ind2 = " + result[1] + " | sumMax = " + result[2]);
			if ( ind1 == result[0] && ind2 == result[1] && sumMax == result[2] ){
				System.out.println("Valid test !");
			}else{
				System.err.println("Error test !");
			}
		} else {
			System.out.println("Borderline case : negative table");
		}
	}
	void EffectivenessTestBiggestSum1 () {
	System.out.println("**START TEST EFFECTIVENESS BiggestSum1()**");
        //Local variables
        int[] tab;
        int n;
        int[] indice;
        long t1, t2, diffT;
        //Initialization
        n = 1000; //(int) Math.pow(2, 15);
        //Multiplying n by "2" in each round
        //10 experiences
        for ( int i = 1; i <= 10; i++ ) {
            tab = new int[n];
            for (int j = 0; j < n; j++) {
				tab[j] = pullAleatory(-100, 100);
			}
            cnt = 0; //Global variable "long".
            t1 = System.nanoTime();
            indice = biggestSum1(tab, n);
            t2 = System.nanoTime();
            diffT = (t2 - t1); //In nanoseconds
            System.out.println ( "Tps = " + diffT + " ns" );
            System.out.println ( "cnt/n^3 = " +
            (double)cnt/(Math.pow(n, 3)) + " constant = 0.16" );
            //Multiplying n by "2" in each round
            n = n * 2;
        }
        System.out.println("**END TEST EFFECTIVENESS BiggestSum1()**"); 
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	
	/** Method biggestSum2
	* Determine the subsequence whose sum of integers is maximal, in an array.
	* (Slow)
	* @param tab - The array used by the method.
	* @param nbElem - The number of elements in the array.
	* @return result - Table containing the indexes of the subsequence and the biggest sum.
	*/
	int[] biggestSum2(int[] tab, int nbElem) {
		int[] result = new int[3];
		if (tab != null && nbElem <= tab.length) {
			int sumMax = 0;
			int sum = 0;
			
			for (int i = 0; i < nbElem; i++) {
				sum = 0;
				for (int j = i; j < nbElem; j++) {
					sum = sum + tab[j];
					if (sum == sumMax) {
						if (j-i < result[1]-result[0]) {
							result[0] = i;
							result[1] = j;
						}
					}
					if (sum > sumMax) {
						sumMax = sum;
						result[2] = sumMax;
						result[0] = i; //i corresponds to index 1
						result[1] = j; //i corresponds to index 2
					}
					cnt = cnt + 1;
				}
			}
		}
		else {
			System.err.println ("ERROR : incorrectly set up"); 
			result[0] = -1; result[1] = -1; //Reporting the error
		}
		if (result[0] == 0 && result[1] == 0 && result[2] == 0) result = new int[0];
		return result;
	}
	/**
	* Test method for the method BiggestSum2
	*/
	void testBiggestSum2() {
		System.out.println("**START TEST biggestSum2()**");
		int tab1[] = {1,2,4,5,7,10}; // Max Sum = 29 | an index pair (0,5)
		int tab2[] = {400,-25 ,-12 ,-100}; // Max Sum = 400 | an index pair (0,0)
		int tab3[] = {-3,6,27,-12,4,-23,2,18}; // Max Sum = 33 | an index pair (1,2)
		int tab4[] = {1,1,1,1,2,-6,3,3}; // Max Sum = 6 | an index pair (6,7)
		int tab5[] = {1,2,4,5,7}; // Max Sum = 19 | an index pair (0,4)
		
		//borderline case
		int tab6[] = {-77,-43,-54,-26,-2}; // Max Sum = 0 | an index pair (null, null)
		int[] tab7  = {2000};
		
		testCaseBiggestSum2(tab1,tab1.length,0,5,29);
		testCaseBiggestSum2(tab2,tab2.length,0,0,400);
		testCaseBiggestSum2(tab3,tab3.length,1,2,33);
		testCaseBiggestSum2(tab4,tab4.length,6,7,6);
		testCaseBiggestSum2(tab5,tab5.length,0,4,19);
		testCaseBiggestSum2(tab6,tab6.length,0,0,0); //Index = 0 but subsequence = null
		testCaseBiggestSum2(tab7,tab7.length,0,0,2000);
		
		System.out.println("**END TEST biggestSum2()**");
	}
	/**
	* Test a request to the method BiggestSum2
	* @param tab - The array used by the method.
	* @param nbElem - The number of elements in the array.
	* @param ind1 - index for automatic verification
	* @param ind2 - index for automatic verification
	* @param sumMax - biggest sum for automatic verification
	*/
	void testCaseBiggestSum2(int[] tab, int nbElem, int ind1, int ind2, int sumMax) {
		System.out.println("table in settings"); 										
		displayTab(tab,nbElem);
		int result[] = biggestSum2(tab, nbElem);
		if (result.length != 0) {
			System.out.println
			("Expected result : ind1 = " + ind1 + " | ind2 = " + ind2 + " | sumMax = " + sumMax);
			System.out.println
			("Result : ind1 = " + result[0] + " | ind2 = " + result[1] + " | sumMax = " + result[2]);
			if ( ind1 == result[0] && ind2 == result[1] && sumMax == result[2] ) {
				System.out.println("Valid test !");
			}
			else {
				System.err.println("Error test !");
			}
		}
		else {
			System.out.println("Borderline case: negative table");
		}
	}
	void EffectivenessTestBiggestSum2() {
		System.out.println("**START TEST EFFECTIVENESS BiggestSum2()**"); 
		//Local variables
		int[] tab;
		int n;
		int[] indice;
		long t1, t2, diffT;
		//Initialization
		n = (int) Math.pow(2,15);
		//Multiplying n by "2" in each round
		//6 experiences
		for ( int i = 1; i <= 6; i++ ) {
			tab = new int[n];
			for (int j = 0; j < n; j++) {
				tab[j] = pullAleatory(-100, 100);
			}
			cnt = 0; //Global variable "long".
			t1 = System.nanoTime();
			indice = biggestSum2(tab, n);
			t2 = System.nanoTime();
			diffT = (t2 - t1); //In nanoseconds
			System.out.println ( "Tps = " + diffT + " ns" );
			System.out.println ( "cnt/n^2 = " +
			(double)cnt/(Math.pow(n, 2)) + " constant = 0.5" );
			//Multiplying n by "2" in each round
			n = n * 2;
		}
		System.out.println("**END TEST EFFECTIVENESS BiggestSum2()**"); 
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	
	/** Method biggestSum3
	 * Determine the subsequence whose sum of integers is maximal, in an array.
	 * (Fast)
	 * @param tab - The array used by the method.
	 * @param ind1 - Left index of the array.
	 * @param ind2 - Right index of the array.
	 * Used to locate the medians in each division of the array.
	 * @return result - Table containing the indexes of the subsequence and the biggest sum.
	 */
	int [] biggestSum3 (int [] tab,int ind1, int ind2) {
		int sum1,sumMax1,indParty1; 
		int sum2,sumMax2,indParty2;
		if(ind1==ind2) {
			int [] result = new int [3];
			result[2] = tab[ind2];
			result[0] = ind1; result[1] = ind2;	
			return result;
		}else {
			int [] result = new int [3];
			double medDouble = (ind1+ind2)/2;	//medium
			int med = (int) Math.round(medDouble);	
			int[] t1 = biggestSum3(tab, ind1, med );
			int[] t2 = biggestSum3(tab,  med+1, ind2 ); 
			// calculation of the largest sum on horseback 
			// divide the table by two each round
			sum1 = tab[med];
			sumMax1 = sum1;
			indParty1 = med;
			for(int k = med-1; k >= ind1; k--){							// k : (nbt+1) + 7nbt = 1 + 8nbt
				sum1 = sum1 + tab[k];
				cnt++;
				if( sum1 > sumMax1 ){
					sumMax1 = sum1; 
					indParty1 = k;
				}
			}
			sum2 = tab[med+1];
			sumMax2 = sum2; 
			indParty2 = med+1;
			for(int i = med+2; i <= ind2; i++){							// k : 1 + 8nbt
			   sum2 = sum2 + tab[i];
			   cnt++;
			   if( sum2 > sumMax2 ){
				   sumMax2 = sum2; 
				   indParty2 = i;
				}
			}
		   int sum3 = sumMax1 + sumMax2;
			if ( t1.length != 0 && t2.length != 0) { 
				if ( t1[2] >= t2[2] && t1[2] >= sum3) {
					if ( t1[0] == 0 && t1[1] == 0 && t1[2] < 0 ) {
						t1 = new int[0];
					}
					return t1;
				}else if ( t2[2] >= t1[2] && t2[2] >= sum3) {
					if ( t2[0] == 0 && t2[1] == 0 && t2[2] < 0 ) {
						t2 = new int[0];
					}
					return t2;
				}else{
					result[2] = sum3;
					result[0] = indParty1;
					result[1] = indParty2;
					if ( result[0] == 0 && result[1] == 0 && result[2] < 0 ) {
						result = new int[0];
					}
					return result;
				}
			}else {
				result = new int [0];
				return result;
			}
			
		}
	}
	/**
	* Test method for the method BiggestSum3
	*/
	void testBiggestSum3 () {
		System.out.println("**TEST biggestSum3 ()**"); 
		//standard case
		int[] tab1  = {1,2,4,5,7,10}; // Max Sum = 29 | an index pair (0,5)
		int[] tab2  = {400,-25 ,-12 ,-100}; // Max Sum = 400 | an index pair (0,0)
		int[] tab3  = {-3,6,27,-12,4,-23,2,18}; // Max Sum = 33 | an index pair (1,2)
		int[] tab4  = {1,1,1,1,2,-6,3,3}; // Max Sum = 6 | an index pair (6,7) 
		int[] tab5  = {1,2,4,5,7}; // Max Sum = 19 | an index pair (0,4)
		//borderline case
		int[] tab7  = {-1,-2,-5,-37,-24}; // Negative table
		int[] tab6  = {2000};
		
		testCaseBiggestSum3(tab1,0,tab1.length-1,0,5,29);
		testCaseBiggestSum3(tab2,0,tab2.length-1,0,0,400);
		testCaseBiggestSum3(tab3,0,tab3.length-1,1,2,33);
		testCaseBiggestSum3(tab4,0,tab4.length-1,6,7,6);
		testCaseBiggestSum3(tab5,0,tab5.length-1,0,4,19);
		testCaseBiggestSum3(tab6,0,tab6.length-1,0,0,2000);
		testCaseBiggestSum3(tab7,0,tab7.length-1,0,0,0);
		
		System.out.println("**END TEST biggestSum3 ()**"); 
	}
	/**
	* Test a request to the method BiggestSum3
	* @param tab - The array used by the method.
	* @param nbElem1 - Start of the array.
	* @param nbElem2 - End of the array.
	* @param ind1 - index for automatic verification
	* @param ind2 - index for automatic verification
	* @param sumMax - biggest sum for automatic verification
	*/
	void testCaseBiggestSum3 (int [] tab,int nbElem1, int nbElem2, int ind1, int ind2, int sumMax) {
		System.out.println("table in settings"); 										
		displayTab(tab,nbElem2+1);
		int result [] = biggestSum3(tab,nbElem1,nbElem2);
		if ( result.length != 0) {
			System.out.println
			("Expected result : ind1 = " + ind1 + " | ind2 = " + ind2 + " | sumMax = " + sumMax);
			System.out.println
			("Result : ind1 = " + result[0] + " | ind2 = " + result[1] + " | sumMax = " + result[2]);
			if ( ind1 == result[0] && ind2 == result[1] && sumMax == result[2] ){
				System.out.println("Valid test !");
			}else{
				System.err.println("Error test !");
			}
		} else {
			System.out.println("Borderline case : negative table");
		}
	}
	void EffectivenessTestBiggestSum3 () {
	System.out.println("**START TEST EFFECTIVENESS BiggestSum3()**"); 
		//Local variables
		int[] tab;
		int n;
		int[] indice;
		long t1, t2, diffT;
		double nlog2n;
		//Initialization
		n = (int) Math.pow(2,15);
		//Multiplying n by "2" in each round
		//6 experiences
		for ( int i = 1; i <= 6; i++ ) {
			tab = new int[n];
			for (int j = 0; j < n; j++) {
				tab[j] = pullAleatory(-100, 100);
			}
			cnt = 0; //Global variable "long".
			t1 = System.nanoTime();
			indice = biggestSum3(tab, 0, tab.length-1);
			t2 = System.nanoTime();
			diffT = (t2 - t1); //In nanoseconds
			nlog2n = n * ( Math.log10(n)/Math.log10(2) );
			System.out.println ( "Tps = " + diffT + " ns" );
			System.out.println ( "cnt/nlog2n = " +
			(double)cnt/nlog2n + " constant = 0.7" );
			//Multiplying n by "2" in each round
			n = n * 2;
		}
		System.out.println("**END TEST EFFECTIVENESS BiggestSum3()**"); 	
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	
	/** Method biggestSum4
	* Determine the subsequence whose sum of integers is maximal, in an array.
	* (Very fast)
	* @param tab - The array used by the method.
	* @param nbElem - The number of elements in the array.
	* @return result - Table containing the indexes of the subsequence and the biggest sum.
	*/
	int[] biggestSum4(int[] tab, int nbElem) {
		int[] result = new int[3];
		if (tab != null && nbElem <= tab.length) {
			int sumMax = 0;
			int sum = 0;
			int startBS = 0; //startBiggestSum
			
			for (int i = 0; i < nbElem; i++) {
				sum = sum + tab[i];
				if (sum >= sumMax) {
					sumMax = sum;
					result[0] = startBS;
					result[1] = i;
					result[2] = sumMax;
				}
				if (sum <= 0) {
					sum = 0;
					startBS = i + 1;
				}
				cnt = cnt + 1;
			}
		}
		else {
			System.err.println ("ERROR : incorrectly set up"); 
			result[0] = -1; result[1] = -1; //Reporting the error
		}
		if (result[0] == 0 && result[1] == 0 && result[2] == 0) result = new int[0];
		return result;
	}
	/**
	* Test method for the method BiggestSum4
	*/
	void testBiggestSum4() {
		System.out.println("**START TEST biggestSum4()**");
		int tab1[] = {1,2,4,5,7,10}; // Max Sum = 29 | an index pair (0,5)
		int tab2[] = {400,-25,-12,-100}; // Max Sum = 400 | an index pair (0,0)
		int tab3[] = {-3,6,27,-12,4,-23,2,18}; // Max Sum = 33 | an index pair (1,2)
		int tab4[] = {1,1,1,1,2,-6,3,3}; // Max Sum = 6 | an index pair (6,7)
		int tab5[] = {1,2,4,5,7}; // Max Sum = 19 | an index pair (0,4)
		
		//borderline case
		int tab6[] = {-1,-2,-4,-5,-7}; // Max Sum = 0 | an index pair (null, null)
		int[] tab7  = {2000};
		
		testCaseBiggestSum4(tab1,tab1.length,0,5,29);
		testCaseBiggestSum4(tab2,tab2.length,0,0,400);
		testCaseBiggestSum4(tab3,tab3.length,1,2,33);
		testCaseBiggestSum4(tab4,tab4.length,6,7,6);
		testCaseBiggestSum4(tab5,tab5.length,0,4,19);
		testCaseBiggestSum4(tab6,tab6.length,0,0,0); //Index = 0 but subsequence = null
		testCaseBiggestSum4(tab7,tab7.length,0,0,2000);
		
		System.out.println("**END TEST biggestSum4()**");
	}
	/**
	* Test a request to the method BiggestSum4
	* @param tab - The array used by the method.
	* @param nbElem - The number of elements in the array.
	* @param ind1 - index for automatic verification
	* @param ind2 - index for automatic verification
	* @param sumMax - biggest sum for automatic verification
	*/
	void testCaseBiggestSum4(int[] tab, int nbElem, int ind1, int ind2, int sumMax){
		System.out.println("table in settings"); 										
		displayTab(tab,nbElem);
		int result[] = biggestSum4(tab, nbElem);
		if (result.length != 0) {
			System.out.println
			("Expected result : ind1 = " + ind1 + " | ind2 = " + ind2 + " | sumMax = " + sumMax);
			System.out.println
			("Result : ind1 = " + result[0] + " | ind2 = " + result[1] + " | sumMax = " + result[2]);
			if ( ind1 == result[0] && ind2 == result[1] && sumMax == result[2] ) {
				System.out.println("Valid test !");
			}
			else {
				System.err.println("Error test !");
			}
		}
		else {
			System.out.println("Borderline case: negative table");
		}
	}
	void EffectivenessTestBiggestSum4(){
		System.out.println("**START TEST EFFECTIVENESS BiggestSum4()**"); 
		//Local variables
		int[] tab;
		int n;
		int[] indice;
		long t1, t2, diffT;
		//Initialization
		n = (int) Math.pow(2,15);
		//Multiplying n by "2" in each round
		//6 experiences
		for ( int i = 1; i <= 6; i++ ) {
			tab = new int[n];
			for (int j = 0; j < n; j++) {
				tab[j] = pullAleatory(-100, 100);
			}
			cnt = 0; //Global variable "long".
			t1 = System.nanoTime();
			indice = biggestSum4(tab, n);
			t2 = System.nanoTime();
			diffT = (t2 - t1); //In nanoseconds
			System.out.println ( "Tps = " + diffT + " ns" );
			System.out.println ( "cnt/n = " +
			(double)cnt/n + " constant = 1" );
			//Multiplying n by "2" in each round
			n = n * 2;
		}
		System.out.println("**END TEST EFFECTIVENESS BiggestSum4()**"); 
	}
	
	//------------------------------------------------------------------
	//------------------------------------------------------------------
	
	//UTILITY CODE !!!! (Display of tables and random numbers)
	/**
	* Method displayTab - Display the table 
	* @param tab - selected table
	* @param nbElem  - number of indices shown in the table
	**/
	void displayTab(int [] tab, int nbElem) {
		System.out.println();	
		for ( int k = 0; k < nbElem; k++) {	
			System.out.println("tab["+k+"] = " + tab[k]);	
		} 
		System.out.println();	
	}
	/**
	* Returns a random integer between min and max (min <= value <= max).
	* Check that min <= max and min >= 0 and max >= 0, otherwise display
	* an error (and in this case return -1).
	* @param min - the value of the minimum integer
	* @param max - the value of the maximum integer
	* @return the random integer
	*/
	int pullAleatory(int min, int max) {
		int random;
		if (min <= max && min >= 0) {
			double r = Math.random();
			r = r * (max - min + 1) + min;
			random = (int) r;
		}
		else {
			random = -1;
		}
		return random;
	}
}
