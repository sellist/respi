import machine
from switch import Switch
from machine import Pin

board_led = Pin("LED", Pin.OUT)

def main():

    switch_pin = machine.Pin(16, machine.Pin.IN, machine.Pin.PULL_DOWN)
    button_one = Switch(switch_pin)

    while True:

        my_switch_new_value = False

        # Disable interrupts for a short time to read shared variable
        irq_state = machine.disable_irq()
        if button_one.new_value_available:
            my_switch_value = button_one.value
            my_switch_new_value = True
            button_one.new_value_available = False
        machine.enable_irq(irq_state)

        # If my switch had a new value, print the new state
        if my_switch_new_value:
            if my_switch_value:
                print("Switch Opened")
                board_led.on()
            else:
                print("Switch Closed")
                board_led.off()

main()