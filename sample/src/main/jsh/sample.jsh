import com.mickey305.foundation.v3.compat.lang.Math;
import com.mickey305.foundation.v3.lang.StackFinder;

import java.util.Objects;

void test1() {
  long res = Math.addExact(12, 12);
  StackTraceElement elm = StackFinder.tryGet(StackFinder.Position.thisCodeBlock().caller());
  assert elm != null;
  System.out.println(elm.toString() + ", result=" + res);
}

void test2() {
  long res = Math.addExact(1000000000, 2000000000);
  StackTraceElement elm = StackFinder.tryGet(StackFinder.Position.thisCodeBlock().caller());
  assert elm != null;
  System.out.println(elm.toString() + ", result=" + res);
}

int getLineNumber() {
  StackTraceElement elm = StackFinder.tryGet(StackFinder.Position.thisCodeBlock().caller());
  assert elm != null;
  return Objects.requireNonNull(elm).getLineNumber();
}

//test1();
//test2();
//getLineNumber();
