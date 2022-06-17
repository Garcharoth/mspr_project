var app = new Vue({
    el: '#app',
    data() {
        return {
            customers: [],
            newCustomer: {},
            create: false,
            update: false,
            updateCustomer: {}
        }
    },
    mounted() {
        axios.get('/salesman/add_customer')
            .then(response => {
                this.customers = response.data.data;
            })
    },
    methods: {
        createCustomer() {
            axios.post('/salesman/add_customer', this.newCustomer)
                .then(response => {
                    if (response.data.success) {
                        this.newCustomer = {};
                        this.create = false;
                        axios.get('/salesman/add_customer')
                            .then(response => {
                                this.customers = response.data.data;
                            })
                    }
                })
        },
        updateCustomer() {
            let customerId = localStorage.getItem('customer.id');
            axios.put('/salesman/update_customer?customerId=' + customerId)
                .then(response => {
                    this.updateCustomer = response.data.data;
                })
        },
        updateItem() {
            this.update = !this.update
        },
        saveUpdate() {

        }
    }
})