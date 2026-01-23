import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    public void addFirstTestWithAddLastTest(){
        Deque61B<String> deque1 = new ArrayDeque61B<>();
        assertThat(deque1.isEmpty()).isTrue();
        deque1.addFirst("a");
        assertThat(deque1.isEmpty()).isFalse();
        deque1.addFirst("b");
        assertThat(deque1.get(0)).isEqualTo("b");
        assertThat(deque1.toList()).containsExactly("b", "a").inOrder();
        deque1.addFirst("c");
        assertThat(deque1.toList()).containsExactly("c", "b", "a").inOrder();
        deque1.addLast("d");
        assertThat(deque1.toList()).containsExactly("c", "b", "a", "d").inOrder();
    }

    @Test
    public void resizeTest(){
        Deque61B<Integer> deque61B = new ArrayDeque61B<>();
        for(int i = 0; i < 10; i++) deque61B.addFirst(i);
        assertThat(deque61B.toList()).containsExactly(9, 8, 7, 6, 5, 4, 3, 2, 1, 0).inOrder();
    }
}
