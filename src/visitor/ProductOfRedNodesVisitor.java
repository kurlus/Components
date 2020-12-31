package visitor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class ProductOfRedNodesVisitor extends TreeVis {

	private BigInteger result = new BigInteger("1");

	public int getResult() {
		//implement this
		return result.mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))).intValue();
	}

	public void visitNode(TreeNode node) {
		if (node.getColor() == Color.RED)
		{
			int value = node.getValue() == 0 ? 1 : node.getValue();
			result = result.multiply(new BigInteger(String.valueOf(value)).mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))));
		}	
	}

	public void visitLeaf(TreeLeaf leaf) {
		if (leaf.getColor() == Color.RED)
		{
			int value = leaf.getValue() == 0 ? 1 : leaf.getValue();
			result = result.multiply(new BigInteger(String.valueOf(value)).mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))));
		}	
	}	

}
