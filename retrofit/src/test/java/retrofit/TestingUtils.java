// Copyright 2013 Square, Inc.
package retrofit;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import retrofit.mime.MimeHelper;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedOutput;

import static org.assertj.core.api.Assertions.assertThat;

public final class TestingUtils {
  public static Method getMethod(Class c, String name) {
    for (Method method : c.getDeclaredMethods()) {
      if (method.getName().equals(name)) {
        return method;
      }
    }
    throw new IllegalArgumentException("Unknown method '" + name + "' on " + c);
  }

  public static TypedOutput createMultipart(Map<String, TypedOutput> parts) {
    MultipartTypedOutput typedOutput = MimeHelper.newMultipart("foobarbaz");
    for (Map.Entry<String, TypedOutput> part : parts.entrySet()) {
      typedOutput.addPart(part.getKey(), part.getValue());
    }
    return typedOutput;
  }

  public static void assertBytes(byte[] bytes, String expected) throws IOException {
    assertThat(new String(bytes, "UTF-8")).isEqualTo(expected);
  }
}
