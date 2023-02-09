/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread{
    
    private Queue<Integer> queue;

    private long size;
    
    
    public Consumer(Queue<Integer> queue){

        this.queue=queue;
    }

    public Consumer(Queue<Integer> queue,long size){
        this.queue=queue;
        this.size = size;
    }
    
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                if (queue.size() == size) {
                    queue.notify();
                    for(int i = 0; i < queue.size();i++){
                        int elem = queue.poll();
                        System.out.println("Consumer consumes " + elem);
                    }

                } else {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        }
    }
}
