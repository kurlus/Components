package asim.visitor;

import java.math.BigInteger;

public class FancyVisitor extends TreeVis 
{
	private BigInteger result = new BigInteger("0");
	private BigInteger nodeSum = new BigInteger("0");
	private BigInteger leafSum = new BigInteger("0");

	public int getResult() {
		//implement this
		result = nodeSum.subtract(leafSum);
		return result.abs().mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))).intValueExact();
	}

	public void visitNode(TreeNode node) {
		int depth = node.getDepth();

		if (depth == 0)
			nodeSum = nodeSum.add(new BigInteger(String.valueOf(node.getValue())).mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))));
		else if (depth%2 == 0)
			nodeSum = nodeSum.add(new BigInteger(String.valueOf(node.getValue())).mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))));
	}

	public void visitLeaf(TreeLeaf leaf) {
		if (leaf.getColor() == Color.GREEN)
			leafSum = leafSum.add(new BigInteger(String.valueOf(leaf.getValue())).mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7))));
	}
}
