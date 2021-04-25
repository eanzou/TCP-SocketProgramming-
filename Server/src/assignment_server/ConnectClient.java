 /*
 * Teoh Ean Zou
 * SWE1804433
 * Tan Tian Lim
 * SWE1804420
 */

package assignment_server;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import static java.lang.Integer.*;
import java.net.ServerSocket;
import java.util.concurrent.ThreadLocalRandom;

public class ConnectClient {

    Server_Page jta;
    Socket socket;
    ServerThread client;
    int colour_num = 0;
    Color DARK_GREEN = new Color(0, 102, 0);
    Color BROWN = new Color(102, 51, 0);
    Color AQUA = new Color(0, 102, 102);
    Color PURPLE = new Color(102, 0, 153);
    Color DARK_PINK = new Color(204, 0, 102);
    Color[] colour_list = {Color.BLACK, Color.BLUE, DARK_GREEN, BROWN, AQUA, PURPLE, DARK_PINK, Color.DARK_GRAY};
     
     public ConnectClient(Server_Page jta, ServerSocket server) throws ClassNotFoundException, IOException
     {            
        try
        {
            while (true)
            {
                socket = server.accept(); //if got new client link in, connect 
                client = new ServerThread(socket, jta); //create new client thread
                client.start(); //start new client thread
                
                if (colour_num < 7) //only got 8 colours so there will be limit (6 can + till 7 then need reset)
                    colour_num++;
                else 
                    colour_num = 0; //if more than 8 client join then reset colour
            }
        } catch (IOException ex) {
            //if server socket closed by user
            jta.appendMsg("Server Socket is closed", Color.BLACK);
            if (client != null) //if there are clients connecting
            {
                client.stop(); //stop the thread
            }
            System.exit(0); //exit the program
        }
     }
     
    class ServerThread extends Thread{
        Socket socket = null;
        Server_Page jta;
        ObjectInputStream ois; 
        ObjectOutputStream oos;
        String user;
        String status = "fail";
        int my_colour = colour_num; 
       
        public ServerThread(Socket socket, Server_Page jta) throws IOException, ClassNotFoundException
        {
            this.socket=socket;
            this.jta = jta;
            
            ois = new ObjectInputStream(socket.getInputStream());
        }
        
        public void run() {
            
            String client_page; 
            int client_choose;
            String[] choice = {"Parcel", "Shopping", "Food", "House Moving"}; //array of choices in string
            String pickup = null, dropoff = null, vehicle = null, shop = null;
            int num_worker = 1, num_vehicle = 1;
            int takeonce=0;
            int error=0;
            int cart_item = 0;
            String chosen_category = null;
            String[][] items_in_cart = null;
            String[][] food_menu = null;
            String summary_type;
            int price_travel, total;

            try
            {
                jta.text_colour = colour_list[my_colour];
                user = (String) ois.readObject();
                jta.appendMsg("Client " + user + " successfully logged in\n", colour_list[my_colour]);
                
            while (true)
            {
                 client_page = (String) ois.readObject(); //read which page they in right now
                 jta.appendMsg("Client " + user + " is on " + client_page + " page" + "\n", colour_list[my_colour]); 

                 switch (client_page)
                 {
                     case "Category":
                         client_choose = (int) ois.readObject();
                         jta.appendMsg("Client " + user + " choose: " + choice[client_choose] + '\n' + '\n', colour_list[my_colour]);
                         break;

                     case "Parcel", "Moving":
                         chosen_category = client_page; //make a current record
                         if ("Back".equals((String) ois.readObject())) //break operation if client select back button
                             break;
                         jta.appendMsg( "Client " + user + ": \n", colour_list[my_colour]);

                         pickup = (String) ois.readObject();
                         jta.appendMsg("Pickup point: " + pickup + '\n', colour_list[my_colour]);

                         dropoff = (String) ois.readObject();
                         jta.appendMsg("Dropoff point: " + dropoff + '\n', colour_list[my_colour]);

                         vehicle = (String) ois.readObject();
                         jta.appendMsg("Vehical Type: " + vehicle + '\n', colour_list[my_colour]);

                         if (client_page.equals("Moving")) //extra for house moving category
                         {
                             String num; //temporary value
                             num = (String) ois.readObject();
                             jta.appendMsg("Number of workers selected: " + num + '\n', colour_list[my_colour]);
                             num_worker = parseInt(num); //change into int and store

                             num = (String) ois.readObject();
                             jta.appendMsg("Number of vehicles selected: " + num + '\n', colour_list[my_colour]);
                             num_vehicle = parseInt(num);
                         }

                         if (takeonce==0) //only get outputstream ONCE
                         {
                             oos = new ObjectOutputStream(socket.getOutputStream());
                             takeonce=1; //increase the count right after to prevent obtaining it again
                         }

                         if (pickup.equals(dropoff))
                         {   
                             //jta.setForeground(Color.red);
                             if (client_page.equals("Parcel"))
                                 jta.appendMsg("Pickup and Dropoff location is the same" + '\n', Color.RED);
                             if (client_page.equals("Moving"))
                                 jta.appendMsg("Origin and Dropoff location is the same" + '\n', Color.RED);

                             jta.appendMsg("Sending an error message to the client..." + '\n', Color.RED);

                             error = 1;
                             oos.writeObject(error); //send true
                             oos.flush();
                         }
                         else
                         {
                             error = 0;
                             oos.writeObject(error); //send false
                             oos.flush();
                         }

                         jta.appendMsg("\n", colour_list[my_colour]);
                         break;
                         
                     case "Summary":                        
                         int[] price_extra = new int[3];
                         summary_type = "normal";
                         price_travel = calculateTravel(pickup, dropoff, summary_type);
                         jta.appendMsg("Travel price is " + price_travel + " MYR"+ '\n', colour_list[my_colour]);

                         if (takeonce==0) //only get outputstream ONCE
                         {
                             oos = new ObjectOutputStream(socket.getOutputStream());
                             takeonce=1; //increase the count right after to prevent obtaining it again
                         }

                         oos.writeObject(price_travel); //send price of travel
                         oos.flush();
                         total = price_travel;
                         price_extra = calculateVehicle(vehicle, num_worker, num_vehicle, chosen_category);

                         if (chosen_category.equals("Parcel"))
                         {
                             jta.appendMsg("Vehicle price is " + price_extra[0] + " MYR"+ '\n', colour_list[my_colour]);
                             oos.writeObject(price_extra[0]); //parcel category only need first one
                             oos.flush();

                             total += price_extra[0];
                         }
                         else if (chosen_category.equals("Moving"))
                         {
                             jta.appendMsg("Vehicle price is " + price_extra[0] + " MYR"+ '\n', colour_list[my_colour]);
                             jta.appendMsg(num_vehicle-1 + " extra " + vehicle + "(s) price is " + price_extra[1] + 
                                     " MYR"+ '\n', colour_list[my_colour]);
                             jta.appendMsg(num_worker + " worker(s) price is " + price_extra[2] + " MYR"+ '\n', colour_list[my_colour]);

                             for (int i=0; i<3; i++)
                             {
                                 oos.writeObject(price_extra[i]); //send prices
                                 total += price_extra[i];
                             }
                             oos.flush();
                         }
                         jta.appendMsg("Client " + user + "'s total price is " + total + " MYR"+ '\n', colour_list[my_colour]);
                         oos.writeObject(total); //send price of vehicle
                         oos.flush();
                         jta.appendMsg("\n", colour_list[my_colour]);
                         break;
                    
                    case "ShopMall", "FoodDel":
                         chosen_category = client_page; //make a current record
                         if ("Back".equals((String) ois.readObject())) //break operation if client select back button
                             break;

                         shop = (String) ois.readObject();

                         jta.appendMsg( "Client " + user + ": \n", colour_list[my_colour]);
                         jta.appendMsg("Selected Shop: " + shop + '\n', colour_list[my_colour]);

                         dropoff = (String) ois.readObject();
                         jta.appendMsg("Dropoff point: " + dropoff + '\n', colour_list[my_colour]);
                         jta.appendMsg("\n", colour_list[my_colour]);
                         break;    

                     case "ShopItem", "ShopFood":
                         chosen_category = client_page;
                         if (client_page.equals("ShopFood"))
                         {
                             food_menu = menu(shop);

                             if (takeonce==0) //only get outputstream ONCE
                             {
                                 oos = new ObjectOutputStream(socket.getOutputStream());
                                 takeonce=1; //increase the count right after to prevent obtaining it again
                             }

                             for (int i=0; i<3; i++)
                                 for (int j=0; j<2; j++)
                                     oos.writeObject(food_menu[i][j]);
                         }

                         if ("Back".equals((String) ois.readObject())) //break operation if client select back button
                             break;

                         cart_item = (int) ois.readObject();
                         jta.appendMsg("Client " + user + " ordered " + cart_item + " items \n", colour_list[my_colour]);

                         items_in_cart = new String[cart_item][2]; //create array

                         jta.appendMsg("----------Order List----------\n", colour_list[my_colour]);
                         for (int i=0; i<cart_item; i++)
                         {
                             for (int j=0; j<2; j++)
                             {
                                 items_in_cart[i][j] = (String) ois.readObject();
                                 jta.appendMsg(items_in_cart[i][j] + "\t", colour_list[my_colour]);
                             }
                             jta.appendMsg("\n", colour_list[my_colour]);
                         }
                         jta.appendMsg("\n", colour_list[my_colour]);

                         if (cart_item == 0)
                         {
                             jta.appendMsg("Client cart empty" + '\n', Color.RED);
                             jta.appendMsg("Sending an error message to the client..." + '\n', Color.RED);
                         }
                         break;

                     case "SummaryShop":   
                         summary_type = "shop";
                         int food_del_price = 8;                      
                         if (chosen_category.equals("ShopItem"))
                             price_travel = calculateTravel(shop, dropoff, summary_type);
                         else //fooddel
                             price_travel = food_del_price; //fixed for food delivery

                         jta.appendMsg("Travel price is " + price_travel + " MYR"+ '\n', colour_list[my_colour]);

                         if (takeonce==0) //only get outputstream ONCE
                         {
                             oos = new ObjectOutputStream(socket.getOutputStream());
                             takeonce=1; //increase the count right after to prevent obtaining it again
                         }

                         oos.writeObject(price_travel); //send price of travel
                         oos.flush(); 

                         if (chosen_category.equals("ShopFood"))
                         {
                             total = price_travel;

                             for (int i=0; i<cart_item; i++)
                             {
                                 for (int j=0; j<3; j++)
                                 {
                                     if (items_in_cart[i][0].equals(food_menu[j][0])) //compare with the one in default
                                         total += parseInt(food_menu[j][1])*parseInt(items_in_cart[i][1]); //cost times quantity
                                 }
                             }
                             oos.writeObject(total); //send price of travel
                             oos.flush();
                             jta.appendMsg("Client " + user + "'s total price is " + total + " MYR"+ '\n', colour_list[my_colour]);
                         }

                         jta.appendMsg("\n", colour_list[my_colour]);
                         break;

                     case "Delivery":
                         int min = 5, max = 15, randomNum;
                         randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                         jta.appendMsg("Client " + user + "'s delivery in progress, estimated time is " 
                                 + randomNum + " seconds\n", colour_list[my_colour]);
                         if (takeonce==0) //only get outputstream ONCE
                         {
                             oos = new ObjectOutputStream(socket.getOutputStream());
                             takeonce=1; //increase the count right after to prevent obtaining it again
                         }
                         oos.writeObject(randomNum); //send random delivery time
                         oos.flush();
                         ois.readObject(); //receive something means parcel delivered
                         jta.appendMsg("Client " + user + " items are delivered!\n", colour_list[my_colour]);
                         jta.appendMsg("\n", colour_list[my_colour]);
                         break;

                     default:
                         //nothing
                 }

            }
        } catch (IOException ex) 
        {
            jta.appendMsg("-------------------------------------------\n", Color.RED);
            jta.appendMsg("Client " + user + " terminated abruptly \n", Color.RED);
        }   catch (ClassNotFoundException ex) 
        { 
            jta.appendMsg("Client " + user + " closed \n", Color.RED);
        } 
        
        finally //if client close 
        {    
            try
            {
                 jta.appendMsg("Connection Closing..\n", Color.RED);
                if (ois!=null)
                {
                    ois.close(); 
                     jta.appendMsg("Socket Input Stream Closed\n", Color.RED);
                }
                if(oos!=null)
                {
                    oos.close();
                     jta.appendMsg("Socket Out Closed\n", Color.RED);
                }
                if (socket!=null)
                {
                    socket.close();
                    jta.appendMsg("Socket Closed\n", Color.RED);
                    jta.appendMsg("-------------------------------------------\n", Color.RED);
                }
            }catch(IOException ie)
            {
                jta.appendMsg("Socket Close Error\n", Color.RED);
            }
        }//end finally
    }

    String[] pickup_list = {"Xiamen University Malaysia", "The Olive", "Sunway University", "Taylor University"};
    String[] dropoff_list = {"Xiamen University Malaysia", "The Olive", "Sunway University", "Taylor University"};
    //fixed price list 
    int[][] pd_price = {{0, 5, 10, 10},
                        {5, 0, 10, 10},
                        {10, 10, 0, 5},
                        {10, 10, 5, 0}}; 
    String[] shop_list = {"Kipmall", "Tesco", "Aeon Nilai", "Sunway Pyramid", "Pavilion"};
    int[][] ds_price = {{5, 8, 8, 15, 15},
                        {5, 8, 8, 15, 15},
                        {15, 15, 10, 5, 8},
                        {15, 15, 10, 5, 8}}; 
    int current_p, current_d, current_s; 
    public int calculateTravel (String pickup, String dropoff, String type)
    {
        for (int i=0; i<4; i++) //convert to int easier to check
        {
            if (type.equals("normal"))
            {
                if (pickup.equals(pickup_list[i]))
                    current_p = i; 
            }

            if (dropoff.equals(dropoff_list[i]))
                current_d = i; 
        }
        
        if (type.equals("shop"))
        {
            for (int i=0; i<5; i++)
                if (pickup.equals(shop_list[i])) //shop
                    current_s = i; 
        }
        
        int price = 0;
        if (type.equals("normal"))
            price = pd_price[current_p][current_d]; //travel price for normal
        else if (type.equals("shop"))
            price = ds_price[current_d][current_s]; //travel price for shop
        
        return price;
    }
    
    String[] vehicle_list_parcel = {"Motor Cycle", "Car", "Pickup Truck", "Van", "1-Ton Lorry", "3-Ton Lorry"};
    String[] vehicle_list_move = {"Pickup Truck", "Van", "1-Ton Lorry", "3-Ton Lorry", "5-Ton Lorry"};
    //fixed vehicle price list
    int[] vp_price = {0, 5, 10, 15, 20, 25};
    int[] vm_price = {200, 250, 400, 500, 600};
    int price_per_worker = 20;
    int current_v;
    public int[] calculateVehicle(String vehicle, int num_worker, int num_vehicle, String type)
    {
        int[] price = new int[3]; 
        
        if (type.equals("Parcel"))
            for (int i=0; i<6; i++)
            {
                if (vehicle.equals(vehicle_list_parcel[i]))
                        current_v = i; 
                price[0] = vp_price[current_v]; //vehicle price
                price[1] = 0; //nth extra here
                price[2] = 0; //nth extra here
            }
        
        else if (type.equals("Moving"))
            for (int i=0; i<5; i++)
            {
                if (vehicle.equals(vehicle_list_move[i]))
                        current_v = i; 
                price[0] = vm_price[current_v]; //vehicle price times num of vehicle
                price[1] = vm_price[current_v] * (num_vehicle-1);//minus one can previously ald gotcount (this are extras)
                price[2] = num_worker * price_per_worker; 
            } 

        return price; 
    }
    
    public String[][] menu(String shop)
    {
        String[] cat = {"MCD", "Pizza Hut", "Secret Recipe", "Sushi Zanmai" , "Rock Cafe", "KOI"}; 
        String[][][]menu_items = {
                                    //mcd
                                    {{"Mc Chicken","10"}, 
                                    {"Filet O' Fish","11"}, 
                                    {"Double Cheese Burger","15"}},
                                    //Pizza
                                    {{"Pepper  oni Pizza","27"}, 
                                    {"Triple Cheeze Pizza","25"}, 
                                    {"Veggie Lover Pizza","24"}},
                                    //secret
                                    {{"Oreo Cheese Cake","98"}, 
                                    {"Chocolate Fudge Cake","98"}, 
                                    {"Sponge Cake","40"}},
                                    //sushi
                                    {{"Chicken Katsu Don","16"}, 
                                    {"Salmon Mentai Roll","21"}, 
                                    {"Shoyu Udon","13"}},
                                    //cafe
                                    {{"Egg fried Rice","8"}, 
                                    {"Tomyum Soup Noodles","12"}, 
                                    {"Nasi Lemak","10"}},
                                    //koi
                                    {{"Milk Tea","7"}, 
                                    {"Green Tea Macchiato","9"}, 
                                    {"Golden Bubble Milk Tea","8"}}
                                };

        String[][] chosen = {};

        for (int i=0; i<6; i++)
        {
            if (shop.equals(cat[i]))
            {
                chosen = menu_items[i];
            }
        }
            
        return chosen;
    }
    }
}
