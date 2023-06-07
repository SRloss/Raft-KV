package net.iems.client;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by 大东 on 2023/2/25.
 */
@Slf4j
public class RaftClient {

    public static void main(String[] args) throws Throwable {

        RaftClientRPC rpc = new RaftClientRPC();
        InetAddress localHost = InetAddress.getLocalHost();
        String prefix = localHost.getHostAddress() + ":" + UUID.randomUUID().toString().substring(0, 5) + ":";
        int cnt = 0;
        // 从键盘接收数据
        Scanner scan = new Scanner(System.in);

        // nextLine方式接收字符串
        System.out.println("Raft client is running, please input command:");

        while (scan.hasNextLine()){
            String input = scan.nextLine();
            if (input.equals("exit")){
                scan.close();
                return;
            }
            String[] raftArgs = input.split(" ");
            int n = raftArgs.length;
            if (n != 2 && n != 3){
                System.out.println("invalid input");
                continue;
            }

            // get [key]
            if (n == 2){
                if (!raftArgs[0].equals("get")){
                    System.out.println("invalid input");
                    continue;
                }
                System.out.println(rpc.get(raftArgs[1], prefix + cnt++));
            }

            // [op] [key] [value]
            if (n == 3){
                if (raftArgs[0].equals("put")){
                    System.out.println(rpc.put(raftArgs[1], raftArgs[2], prefix + cnt++));
                } else if (raftArgs[0].equals("delete")){
                    System.out.println(rpc.del(raftArgs[1], raftArgs[2], prefix + cnt++));
                } else {
                    System.out.println("invalid input");
                }
            }
        }

    }

}
