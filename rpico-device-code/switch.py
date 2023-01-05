import machine


class Switch():  # pylint: disable=too-few-public-methods
    """Switch Class
    Class for defining a switch. Uses internal state to debounce switch in
    software. To use switch, check the "new_value_available" member and the
    "value" member from the application.
    sourced from https://github.com/selfhostedhome/micropython-debounce-switch
    """

    def __init__(self, pin, checks=3, check_period=25):
        self.pin = pin
        self.pin.irq(handler=self._switch_change,
                     trigger=machine.Pin.IRQ_FALLING | machine.Pin.IRQ_RISING)

        self.debounce_timer = machine.Timer(-1)
        self.new_value_available = False
        self.value = None
        self.prev_value = None
        self.debounce_checks = 0
        self.checks = checks
        self.check_period = check_period

    def _switch_change(self, pin):
        self.value = pin.value()
        self.debounce_checks = 0
        self._start_debounce_timer()

        # Disable IRQs for GPIO pin while debouncing
        self.pin.irq(trigger=0)

    def _start_debounce_timer(self):
        self.debounce_timer.init(period=self.check_period, mode=machine.Timer.ONE_SHOT,
                                 callback=self._check_debounce)

    def _check_debounce(self, _):
        new_value = self.pin.value()

        if new_value == self.value:
            self.debounce_checks = self.debounce_checks + 1

            if self.debounce_checks == self.checks:
                if self.prev_value != self.value:
                    self.new_value_available = True
                    self.prev_value = self.value

                # Re-enable the Switch IRQ to get the next change
                self.pin.irq(handler=self._switch_change,
                             trigger=machine.Pin.IRQ_FALLING | machine.Pin.IRQ_RISING)
            else:
                self._start_debounce_timer()
        else:
            self.debounce_checks = 0
            self.value = new_value
            self._start_debounce_timer()
