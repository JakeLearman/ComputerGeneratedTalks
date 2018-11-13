package main.parser;

import main.lexer.Lexeme;

import java.util.*;

public class Generator {

    private HashMap<LexemeList, TreeRoot> roots;
    private Random random;

    public Generator(HashMap<LexemeList, TreeRoot> roots, long seed) {
        this(roots, new Random(seed));
    }

    public Generator(HashMap<LexemeList, TreeRoot> roots) {
        this(roots, new Random());
    }

    public Generator(HashMap<LexemeList, TreeRoot> roots, Random random) {
        this.roots = roots;
        this.random = random;

        // Sort the TreeRoots by Lexeme frequency
        Collection<TreeRoot> values = roots.values();
        for(TreeRoot root : values) {
            ArrayList<TreeLeaf> leafs = root.getLeaves();
            leafs.sort(new Comparison());
        }
    }

    // Generate a string of a given length from the given lexemes.
    public String generate(LexemeList init, int length) {
        length -= init.size(); // We add the init.

        StringBuilder builder = new StringBuilder();

        for(Lexeme x : init.getLexemes()) {
            builder.append(x.getContent());
            builder.append(' ');
        }

        Lexeme next;
        for(int i = 0; i < length; i++) {
            TreeRoot root = roots.get(init);
            if(root == null) break; // reached a dead-end.
            next = this.nextLexeme(root);
            if(next == null) break; // reached a tree with no leaves.

            builder.append(next.getContent());
            builder.append(' ');

            init.remove(0);
            init.add(next);
        }

        return builder.toString();
    }

    // Return the next lexeme from the given root.
    private Lexeme nextLexeme(TreeRoot root) {
        ArrayList<TreeLeaf> leaves = root.getLeaves();

        int denominator = this.sum(leaves);
        int numerator = 0;

        for(TreeLeaf leaf : leaves) {
            numerator += leaf.getFrequency();
            int n = random.nextInt(denominator) + 1;
            if(n <= numerator) return leaf.getLexeme();
        }

        return null; // This only occurs when a TreeRoot has no TreeLeafs (should never happen).
    }

    // Sum all the frequencies within the given list.
    private int sum(ArrayList<TreeLeaf> leaves) {
        int sum = 0;
        for(TreeLeaf leaf : leaves) {
            sum += leaf.getFrequency();
        }
        return sum;
    }

    private class Comparison implements Comparator<TreeLeaf> {

        public int compare(TreeLeaf a, TreeLeaf b) {
            return b.getFrequency() - a.getFrequency(); // Reversed so the collection is sorted high to low.
        }

    }

}
