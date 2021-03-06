package com.rotabuilder.dom.solver.drools;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.rotabuilder.dom.domain.pattern.DayOfWeek;
import com.rotabuilder.dom.domain.employee.Employee;
import com.rotabuilder.dom.domain.shift.ShiftDate;
import com.rotabuilder.dom.domain.pattern.WeekendDefinition;
import com.rotabuilder.dom.domain.contract.Contract;

public class EmployeeConsecutiveAssignmentStart implements Comparable<EmployeeConsecutiveAssignmentStart>,
        Serializable {

    private Employee employee;
    private ShiftDate shiftDate;

    public EmployeeConsecutiveAssignmentStart(Employee employee, ShiftDate shiftDate) {
        this.employee = employee;
        this.shiftDate = shiftDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ShiftDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(ShiftDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof EmployeeConsecutiveAssignmentStart) {
            EmployeeConsecutiveAssignmentStart other = (EmployeeConsecutiveAssignmentStart) o;
            return new EqualsBuilder()
                    .append(employee, other.employee)
                    .append(shiftDate, other.shiftDate)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(employee)
                .append(shiftDate)
                .toHashCode();
    }

    public int compareTo(EmployeeConsecutiveAssignmentStart other) {
        return new CompareToBuilder()
                .append(employee, other.employee)
                .append(shiftDate, other.shiftDate)
                .toComparison();
    }

    @Override
    public String toString() {
        return employee + " " + shiftDate + " - ...";
    }

    public Contract getContract() {
        return employee.getContract();
    }

    public int getShiftDateDayIndex() {
        return shiftDate.getDayIndex();
    }

    public boolean isWeekendAndNotFirstDayOfWeekend() {
        WeekendDefinition weekendDefinition = employee.getContract().getWeekendDefinition();
        DayOfWeek dayOfWeek = shiftDate.getDayOfWeek();
        return weekendDefinition.isWeekend(dayOfWeek) && weekendDefinition.getFirstDayOfWeekend() != dayOfWeek;
    }

    public int getDistanceToFirstDayOfWeekend() {
        WeekendDefinition weekendDefinition = employee.getContract().getWeekendDefinition();
        DayOfWeek dayOfWeek = shiftDate.getDayOfWeek();
        return weekendDefinition.getFirstDayOfWeekend().getDistanceToNext(dayOfWeek);
    }

}
