package epages.util;


/**
 * This Class builds a valid patch json string.
 *
 * @author Bastian Klein
 */
public class PatchBuilder {


    private final StringBuilder sb = new StringBuilder("[\n");


    /**
     * Add key value pair to patch.
     *
     * @param path
     *            Path that should be changed in the patch.
     * @param value
     *            New value for the key.
     */
    public void add(final String path, final String value) {

        sb.append(" {\n");
        sb.append("  \"op\":\"add\",\n");
        sb.append("  \"path\":\"/" + path + "\",\n");
        sb.append("  \"value\":\"" + value + "\"\n");
        sb.append(" },\n");
    }

    @Override
    public String toString() {

        sb.delete(sb.length() - 2, sb.length());
        sb.append("\n]");
        return sb.toString();
    }
}
