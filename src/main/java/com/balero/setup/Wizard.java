/**
 * <pre>
 * Balero CMS Enterprise Edition is free and open source software under MIT License.
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2014 <Balero CMS All Rights Reserved>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * <a href="http://www.balerocms.com">BaleroCMS.com</a>
 * </pre>
 *
 * @author      Anibal Gomez
 * @version     1.0
 * @since       1.0
 */

package com.balero.setup;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

/**
 * Created by lastprophet on 13/07/14.
 */
public class Wizard {

    private String opt;
    private Scanner sc;

    @Autowired
    private com.balero.models.TestDAO TestDAO;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("" +
                ".-. .-')     ('-.                 ('-.  _  .-')                                _   .-')      .-')    \n" +
                "\\  ( OO )   ( OO ).-.           _(  OO)( \\( -O )                              ( '.( OO )_   ( OO ). \n" +
                " ;-----.\\   / . --. / ,--.     (,------.,------.  .-'),-----.          .-----. ,--.   ,--.)(_)---\\_) \n" +
                " | .-.  |   | \\-.  \\  |  |.-')  |  .---'|   /`. '( OO'  .-.  '        '  .--./ |   `.'   | /    _ |  \n" +
                " | '-' /_).-'-'  |  | |  | OO ) |  |    |  /  | |/   |  | |  |        |  |('-. |         | \\  :` `.  \n" +
                " | .-. `.  \\| |_.'  | |  |`-' |(|  '--. |  |_.' |\\_) |  |\\|  |       /_) |OO  )|  |'.'|  |  '..`''.) \n" +
                " | |  \\  |  |  .-.  |(|  '---.' |  .--' |  .  '.'  \\ |  | |  |       ||  |`-'| |  |   |  | .-._)   \\ \n" +
                " | '--'  /  |  | |  | |      |  |  `---.|  |\\  \\    `'  '-'  '      (_'  '--'\\ |  |   |  | \\       / \n" +
                " `------'   `--' `--' `------'  `------'`--' '--'     `-----'          `-----' `--'   `--'  `-----'  \n" +
                "                                                                                  Enterprise Edition\n");
        System.out.println("Welcome to Balero CMS Setup Wizard\n");
        System.out.println("Provide your Database configuration.\n");

        String dbuser;
        System.out.print("Insert MySQL Username\n");
        dbuser = sc.nextLine();

        String dbpass;
        System.out.print("Insert MySQL Password\n");
        dbpass = sc.nextLine();

        String opt;
        System.out.println("The MIT License (MIT)\n" +
                "\n" +
                "Copyright (c) 2014 Balero CMS Enterprise Edition.\n" +
                "\n" +
                "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                "of this software and associated documentation files (the \"Software\"), to deal\n" +
                "in the Software without restriction, including without limitation the rights\n" +
                "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                "copies of the Software, and to permit persons to whom the Software is\n" +
                "furnished to do so, subject to the following conditions:\n" +
                "\n" +
                "The above copyright notice and this permission notice shall be included in\n" +
                "all copies or substantial portions of the Software.\n" +
                "\n" +
                "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n" +
                "THE SOFTWARE.\n" +
                "Agree: Enter Decline: e");
        opt = sc.nextLine();
        exitWizard(opt);


        System.out.print("Setup wizard will create database tables\n" +
                "Continue: Enter or Exit: e\n");
        opt = sc.nextLine();
        exitWizard(opt);


        try {

            System.out.println("" +
                    "     _( )_          _      Mounting CMS...\n" +
                    "   _(     )_      _( )_\n" +
                    "  (_________)   _(     )_\n" +
                    "               (_________)\n" +
                    "    0  1  0               \n" +
                    "       1  0     0  1  0\n" +
                    "          1       1  0");;

            for (double progressPercentage = 0.0; progressPercentage < 1.0; progressPercentage += 0.01) {
                updateProgress(progressPercentage);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {

        }


    }


    public static void exitWizard(String input) {
        if(input.equals("e")) {
            System.exit(1);
        }
    }

    public static void updateProgress(double progressPercentage) {
        final int width = 50; // progress bar width in chars
        int percent = 0;

        System.out.print("\r[");
        int i = 0;
        for (; i <= (int)(progressPercentage*width); i++) {
            percent = (int)(((progressPercentage*width) * 102) / 50);
            System.out.print("=");
        }
        for (; i < width; i++) {
            System.out.print(" ");
        }
        System.out.print("]" + percent + "%");
    }

}
