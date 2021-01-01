package asim.visitor;

import java.math.BigInteger;

public class SumInLeavesVisitor extends TreeVis 
{
	private BigInteger result = new BigInteger("0");

	public int getResult() {
		//implement this
		return result.mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))).intValueExact();
	}

	public void visitNode(TreeNode node) {
		//implement this
	}

	public void visitLeaf(TreeLeaf leaf) {
		//implement this
		result = result.add(new BigInteger(String.valueOf(leaf.getValue())).mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))));

	}
}
