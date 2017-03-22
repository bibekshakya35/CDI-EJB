/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.drose.data.modules.employee.reln.transientdomain;

/**
 *
 * @author bibekshakya
 */
public class SalaryTransaction {
    private double actualSalary = 0;
    private double miscellaneous_allowance = 0;
    private double transportation_cost = 0;
    private double insurance_life = 0;

    private double provident_fund = 0;

    public double getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(double actualSalary) {
        this.actualSalary = actualSalary;
    }

    public double getMiscellaneous_allowance() {
        return miscellaneous_allowance;
    }

    public void setMiscellaneous_allowance(double miscellaneous_allowance) {
        this.miscellaneous_allowance = miscellaneous_allowance;
    }

    public double getTransportation_cost() {
        return transportation_cost;
    }

    public void setTransportation_cost(double transportation_cost) {
        this.transportation_cost = transportation_cost;
    }

    public double getInsurance_life() {
        return insurance_life;
    }

    public void setInsurance_life(double insurance_life) {
        this.insurance_life = insurance_life;
    }

    public double getProvident_fund() {
        return provident_fund;
    }

    public void setProvident_fund(double provident_fund) {
        this.provident_fund = provident_fund;
    }

    
    
}
