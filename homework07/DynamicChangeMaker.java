/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Implementation of minimum ChangeMaker. Uses Tuple.java and tested in
 *                DynamicChangemakerTestHarness.java
 * @author    :  Jay Patel
 * Date       :  2017-04-19
 * Description:  Given a set of denominations, find the minimum number
 *                of coins to make a given amount. D Y N A M I C programming
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

import java.util.*;

/**
* This class is DynamicChangeMaker. Set to final because instantiating one is semantically weird.
*/
public final class DynamicChangeMaker {
  /**
  * Private constructor
  */
  private DynamicChangeMaker() { }

  /**
  * Returns a Tuple representing the minimum number of coins to make a given amount
  *
  * @param denominations  int[] of the denominations
  * @param target  the given amount
  * @return the tuple of minimum number of coins
  */
  public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
    if (!validDenominations(denominations)) {
      System.out.println("Not valid denominations");
      return Tuple.IMPOSSIBLE;
    }
    if (!validTarget(target)) {
      System.out.println("target can't be negative");
      return Tuple.IMPOSSIBLE;
    }

    int rows = denominations.length;
    int columns = target + 1;
    Tuple[][] table = new Tuple[rows][columns];
    int cost = 0;

    fillZeros(table, denominations);

    for (int row = 0; row < rows; row++) {
      for (int column = 1; column < columns; column++) {

        if (denominations[row] > column) {
          table[row][column] = Tuple.IMPOSSIBLE;
        } else {
          table[row][column] = new Tuple(denominations.length);
          table[row][column].setElement(row, 1);
          cost = column - denominations[row];
          table[row][column] = table[row][cost].isImpossible() ?
            Tuple.IMPOSSIBLE :
            table[row][column].add(table[row][cost]);
        }

        if (row != 0) { table[row][column] = findOptimal(table[row][column], table[row-1][column]); }
      }
    }

    Tuple result = table[rows-1][columns-1];
    return result;
  }

  /**
  * Returns the minimum of the two tuples
  *
  * @param current  the current table cell to be filled
  * @param above    the above table cell that already has an answer
  * @return the minimum of the two tuples
  */
  private static Tuple findOptimal(Tuple current, Tuple above) {
    if (!above.isImpossible()) {
      if (current.isImpossible()) { return above; }
      if (above.total() < current.total()) { return above; }
    }

    return current;
  }

  /**
  * Initializes column zero to zero Tuples of length coins
  *
  * @param table  table of tuples
  * @param coins  the given coins
  */
  private static void fillZeros(Tuple[][] table, int[] coins) {
    for (int row = 0; row < coins.length; row++) {
      table[row][0] = new Tuple(coins.length);
    }
  }

  /**
  * Checks if given denominations are valid. Cannot have negatives or duplicates.
  *
  * @param denominations  int[] of the denominations
  * @return false if not valid. true if valid
  */
  private static boolean validDenominations(int[] denominations) {
    boolean existingNegatives = checkForNegatives(denominations);
    boolean existingDuplicates = checkForDuplicates(denominations);
    return !(existingDuplicates || existingNegatives);
  }

  /**
  * Checks if target is valid.
  *
  * @param target  the given amount
  * @return true if positive
  */
  private static boolean validTarget(int target) {
    return target > 0;
  }

  /**
  * Checks if denominations are negative
  *
  * @param denominations  int[] of the denominations
  * @return false if a denomination is negative
  */
  private static boolean checkForNegatives(int[] denominations) {
    for (int row = 0; row < denominations.length; row++) {
      if (denominations[row] <= 0) { return true; }
    }

    return false;
  }

  /**
  * Checks for duplicate denominations
  *
  * @param denominations  int[] of the denominations
  * @return true if there exists a duplicate denomination
  */
  private static boolean checkForDuplicates(int[] denominations) {
    Set<Integer> coins = new HashSet<Integer>();

    for (int row = 0; row < denominations.length; row++) {
      if (!coins.contains(new Integer(denominations[row]))) {
        coins.add(new Integer(denominations[row]));
      } else { return true; }
    }

    return false;
  }
}
