package qlsv_swing.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import qlsv_swing.qlsv.entity.Customer;
import qlsv_swing.qlsv.entity.Vaccine;
import qlsv_swing.qlsv.func.CustomerFunc;
import qlsv_swing.qlsv.view.CustomerView;

public class StudentController {
    private CustomerFunc studentDao;
    private CustomerView customerView;

    public StudentController(CustomerView view) {
        this.customerView = view;
        studentDao = new CustomerFunc();

        view.addAddStudentListener(new AddStudentListener());
        view.searchCustomerListener(new searchCustomerListener());
        view.showAllCustomerListener(new showAllCustomerListener());
        view.addAddVaccineListener(new AddVaccineListener());

        view.addEdiStudentListener(new EditStudentListener());
        view.addEdiVaccineListener(new EditVaccineListener());

        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addDeleteVaccineListener(new DeleteVaccineListener());

        view.addClearListener(new ClearStudentListener());
        view.addClearVaccineListener(new ClearVaccineListener());

        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
        view.addListVaccineSelectionListener(new ListVaccineSelectionListener());
    }

    public void showStudentView() {
        List<Customer> customerList = studentDao.getListStudents();
        customerView.setVisible(true);
        customerView.showListCustomers(customerList);
    }

    /**
     * Lớp AddStudentListener
     * chứa cài đặt cho sự kiện click button "Add"
     *
     * @author viettuts.vn
     */
    class AddStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.add(customer);
                customerView.showStudent(customer);
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Thêm thành công!");
            }
        }
    }

    class searchCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = customerView.getNameSearch();
            if (name != null) {
                customerView.showListCustomers(studentDao.findAllByName(name));
            } else {
                customerView.showMessage("chưa nhập name search!");
            }
        }
    }

    class showAllCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.showListCustomers(studentDao.getListStudents());

        }
    }

    class AddVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine vaccine = customerView.getVaccineInfo();
            int idCustomer = customerView.getIdCustomer();
            if (vaccine != null && idCustomer != -1) {
                studentDao.addVaccine(idCustomer, vaccine);
//                customerView.showStudent(customer);
                customerView.showListVaccine(studentDao.findById(idCustomer).getVaccines());
                customerView.showMessage("Thêm Vaccine thành công!");
            } else {
                customerView.showMessage("Không thể thêm vì chưa biết thêm cho customer nào!");

            }
        }
    }

    /**
     * Lớp EditStudentListener
     * chứa cài đặt cho sự kiện click button "Edit"
     *
     * @author viettuts.vn
     */
    class EditStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.edit(customer);
                customerView.showStudent(customer);
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Cập nhật thành công!");
            }
        }
    }

    class EditVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine vaccine = customerView.getVaccineInfo();
            int idCustomer = customerView.getIdCustomer();
            if (vaccine != null && idCustomer != -1) {
                studentDao.editVaccine(idCustomer, vaccine);
                customerView.showListVaccine(studentDao.findById(idCustomer).getVaccines());
                customerView.showMessage("Cập nhật thành công!");
            } else {
                if (idCustomer == -1) customerView.showMessage("Không thể sửa vì chưa biết sửa cho customer nào!");
            }
        }
    }

    /**
     * Lớp DeleteStudentListener
     * chứa cài đặt cho sự kiện click button "Delete"
     *
     * @author viettuts.vn
     */
    class DeleteStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getStudentInfo();
            if (customer != null) {
                studentDao.delete(customer);
                customerView.clearStudentInfo();
                customerView.showListCustomers(studentDao.getListStudents());
                customerView.showMessage("Xóa thành công!");
            }
        }
    }

    class DeleteVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vaccine vaccine = customerView.getVaccineInfo();
            int idCustomer = customerView.getIdCustomer();
            if (vaccine != null && idCustomer != -1) {
                studentDao.deleteVaccine(idCustomer, vaccine);
                customerView.clearVaccineInfo();
                customerView.showListVaccine(studentDao.findById(idCustomer).getVaccines());
                customerView.showMessage("Xóa thành công!");
            } else {
                customerView.showMessage("Không thể xóa vì chưa biết xóa cho customer nào!");
            }
        }
    }

    /**
     * Lớp ClearStudentListener
     * chứa cài đặt cho sự kiện click button "Clear"
     *
     * @author viettuts.vn
     */
    class ClearStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.clearStudentInfo();
        }
    }

    class ClearVaccineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            customerView.clearVaccineInfo();
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     *
     * @author viettuts.vn
     */
    class SortStudentGPAListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            studentDao.sortStudentByGPA();
//            customerView.showListStudents(studentDao.getListStudents());
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     *
     * @author viettuts.vn
     */
    class SortStudentNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            studentDao.sortCustomerByName();
            customerView.showListCustomers(studentDao.getListStudents());
        }
    }

    /**
     * Lớp ListStudentSelectionListener
     * chứa cài đặt cho sự kiện chọn student trong bảng student
     *
     * @author viettuts.vn
     */
    class ListStudentSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            customerView.fillCustomerFromSelectedRow();
        }
    }

    class ListVaccineSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            customerView.fillVaccineFromSelectedRow();
        }
    }
}