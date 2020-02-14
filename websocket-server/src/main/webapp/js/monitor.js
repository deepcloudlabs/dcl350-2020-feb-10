class DashboardViewModel {
    constructor() {
        // domain related observables
        this.currencies = ko.observableArray([]);
        this.symbol = ko.observable("GARAN");
        this.symbols = ko.observableArray(["AKBNK", "ASELS", "GARAN", "TCELL"]);
        this.isMonitoring = ko.observable(false);
        this.connected = ko.observable(false);
        this.windowSize = ko.observable(10);
        this.data = {
            labels: ko.observableArray([]),
            datasets: [
                {
                    label: [],
                    backgroundColor: "rgba(220,220,220,0.2)",
                    borderColor: "rgba(220,220,220,1)",
                    pointColor: "rgba(220,220,220,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: ko.observableArray([])
                }
            ]
        };

        // connect to the websocket server
        this.socket = new SockJS("http://localhost:7600/changes");
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.debug = () => {
        }
        this.stompClient.connect({}, (frame) => {
            toastr.success("Connected!");
            this.connected(true);
            this.stompClient.subscribe("/topic/changes",
                (msg) => {
                    if (!this.isMonitoring()) return;
                    let trade = JSON.parse(msg.body);
                    this.data.datasets[0].data.push(Number(trade.p)*Number(trade.q));
                    let now = new Date().toTimeString();
                    now = now.replace(/.*(\d{2}:\d{2}:\d{2}).*/, '$1');
                    this.data.labels.push(now);
                    if (this.data.datasets[0].data().length > this.windowSize()) {
                        let sliceIndex = this.data.datasets[0].data().length - this.windowSize();
                        let slicedData = this.data.datasets[0].data.slice(sliceIndex);
                        this.data.datasets[0].data(slicedData);
                        let slicedLabels = this.data.labels.slice(sliceIndex);
                        this.data.labels(slicedLabels);
                    }
                })
        })
    }

    // i18n
    changeLng = (lng) => {
        i18n.setLng(lng, () => this.i18n());
    };

    i18n = () => {
        $(document).i18n();
    };

    translate = (word) => i18n.t(word) ;

    // starts monitoring
    start = () => {
        this.isMonitoring(true);
        this.data.datasets[0].label = this.symbol();
        toastr.success(i18n.t("messageMonitoringStarted"), "", AppConfig.TOASTR_CONFIG);
    };

    // stops monitoring
    stop = () => {
        this.isMonitoring(false);
        toastr.warning(i18n.t("messageMonitoringStoped"), "", AppConfig.TOASTR_CONFIG);
    };

};

var dashBoardViewModel = new DashboardViewModel();

$(() => {
    i18n.init(AppConfig.I18N_CONFIG, (t) => {
        $(document).i18n();
        ko.applyBindings(dashBoardViewModel);
    });
});