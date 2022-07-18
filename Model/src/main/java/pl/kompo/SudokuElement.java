package pl.kompo;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuElement implements Serializable, Cloneable {
  List<SudokuField> element;

    public void setElement(List<SudokuField> sudokuFields) {
        element = sudokuFields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            if (element.get(i).getFieldValue() > 0 && element.get(i).getFieldValue() < 10) {
                for (int j = i + 1; j < 9; j++) {
                    if (element.get(j).getFieldValue() == element.get(i).getFieldValue()) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        ToStringBuilder napis = new ToStringBuilder(this);
            for (int j = 0; j < 9; j++) {
                napis.append(element.get(j).getFieldValue());
            }
        return napis.toString();
    }

    @Override
    public boolean equals(Object obj) {
        EqualsBuilder equal = new EqualsBuilder();
        equal.append(element, ((SudokuElement) obj).element);
        return equal.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hash = new HashCodeBuilder(17,37);
        hash.append(element);
        return hash.toHashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
