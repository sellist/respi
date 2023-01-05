import machine
from switch import Switch
from machine import Pin, I2C
from pico_i2c_lcd import I2cLcd
import time

board_led = Pin("LED", Pin.OUT)

# lcd api calls and object creation
i2c = I2C(0, sda=Pin(0), scl=Pin(1), freq=400000)
I2C_ADDR = i2c.scan()[0]
lcd = I2cLcd(i2c, I2C_ADDR, 2, 16)

def main():

    switch_pin = machine.Pin(16, machine.Pin.IN, machine.Pin.PULL_DOWN)
    button_one = Switch(switch_pin)

    lcd.clear()
    lcd.putstr("Pico LCD example")
    time.sleep(1)
    
    while True:
        
        lcd.blink_cursor_on()
        board_led.toggle()

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
                lcd.clear()
                lcd.putstr("button on")
            else:
                lcd.clear()
                lcd.putstr("button off")


main()
