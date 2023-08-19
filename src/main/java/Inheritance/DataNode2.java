package Inheritance;

import Abstract.TreeNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DataNode2 extends TreeNode {
    private String someStr;

    public DataNode2(Long id, Long subId, String className, String someStr) {
        super(id, subId, className);
        this.someStr = someStr;
    }
}