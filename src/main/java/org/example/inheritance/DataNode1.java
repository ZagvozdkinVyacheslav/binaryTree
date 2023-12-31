package org.example.inheritance;

import org.example.abstractClasses.TreeNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class DataNode1 extends TreeNode {
    private Double data1;

    public DataNode1(Long id, Long subId, String className, Double data1) {
        super(id, subId, className);
        this.data1 = data1;
    }
    public DataNode1(Long id, Long subId) {
        super(id, subId);
    }
}
