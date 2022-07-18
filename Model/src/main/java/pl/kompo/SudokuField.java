package pl.kompo;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;



public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {
    private int value;

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        ToStringBuilder napis = new ToStringBuilder(this);
        napis.append(value);
        return napis.toString();
    }

    @Override

    public boolean equals(Object obj) {
        EqualsBuilder equal = new EqualsBuilder();
        equal.append(value, ((SudokuField) obj).value);
        return equal.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hash = new HashCodeBuilder(17,37);
        hash.append(value);
        return hash.toHashCode();
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o.getFieldValue() == this.getFieldValue()) {
            return 0;
        } else if (o.getFieldValue() > this.getFieldValue()) {
            return -1;
        } else {
            return 1;
        }

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
