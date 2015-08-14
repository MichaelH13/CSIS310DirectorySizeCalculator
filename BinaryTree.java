import java.util.Iterator;

public class BinaryTree<E>
{

   public Iterator<E> iterator()
   {
      return null;
   }

   public boolean equals(BinaryTree<E> value)
   {
      Iterator<E> thisTree = iterator();
      Iterator<E> valueTree = value.iterator();
      boolean equal = true;

      // Loop through each element in the tree and compare them for equality
      while (equal && thisTree.hasNext() && valueTree.hasNext())
      {
         // If an element is unequal to another element, then set equal to false
         if (!thisTree.next().equals(valueTree.next()))
         {
            equal = false;
         }
      }

      // After we have compared presumably every element in each tree and they
      // are still considered equal, if either tree has a next element then they
      // are unequal, so set equal to false
      if (equal && thisTree.hasNext() || valueTree.hasNext())
      {
         equal = false;
      }

      return equal;
   }
}
