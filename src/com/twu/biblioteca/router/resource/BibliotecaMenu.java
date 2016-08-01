package com.twu.biblioteca.router.resource;

/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaMenu {

    public String GetMainMenu(){
        StringBuilder st = new StringBuilder();
        st.append("****         This is our Main Menu       ****\n")
                .append("*********************************************\n")
                .append("****1.       List Books                  ****\n")
                .append("****2.       CheckOut Books              ****\n")
                .append("****3.       Return Books                ****\n")
                .append("****0.       Quit                        ****\n")
                .append("*********************************************\n")
                .append("please input what your choose:\n");
        return st.toString();
    }
}
