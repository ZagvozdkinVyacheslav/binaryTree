package Abstract;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public abstract class TreeNode implements Comparable<TreeNode>{
    private Long id;
    private Long subId;
    private String className;


    public TreeNode(Long id, Long subId, String className) {
        this.id = id;
        this.subId = subId;
        this.className = className;
    }
    public TreeNode(Long id, Long subId) {
        this.id = id;
        this.subId = subId;
    }
    @Override
    public int compareTo(TreeNode o) {
        if(this.id + this.subId > o.id + o.subId)return 1;
        if(this.id + this.subId == o.id + o.subId)return 0;
        return -1;
    }
}
