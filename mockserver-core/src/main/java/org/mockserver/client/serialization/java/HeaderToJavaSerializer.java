package org.mockserver.client.serialization.java;

import com.google.common.base.Strings;
import org.mockserver.model.Header;
import org.mockserver.model.NottableString;

import java.util.Arrays;
import java.util.List;

import static org.mockserver.client.serialization.java.ExpectationToJavaSerializer.INDENT_SIZE;

/**
 * @author jamesdbloom
 */
public class HeaderToJavaSerializer implements MultiValueToJavaSerializer<Header> {
    @Override
    public String serializeAsJava(int numberOfSpacesToIndent, Header header) {
        StringBuilder output = new StringBuilder();
        output.append(System.getProperty("line.separator")).append(Strings.padStart("", numberOfSpacesToIndent * INDENT_SIZE, ' '));
        output.append("new Header(").append(NottableStringToJavaSerializer.serializeNottableString(header.getName()));
        for (NottableString value : header.getValues()) {
            output.append(", ").append(NottableStringToJavaSerializer.serializeNottableString(value));
        }
        output.append(")");
        return output.toString();
    }

    @Override
    public String serializeAsJava(int numberOfSpacesToIndent, List<Header> headers) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            output.append(serializeAsJava(numberOfSpacesToIndent, headers.get(i)));
            if (i < (headers.size() - 1)) {
                output.append(",");
            }
        }
        return output.toString();
    }

    @Override
    public String serializeAsJava(int numberOfSpacesToIndent, Header... object) {
        return serializeAsJava(numberOfSpacesToIndent, Arrays.asList(object));
    }
}
