angular.module('InvestmentManager', ["ngResource"])

.controller('InvestmentController', ['$scope', 'RequestFactory', function($scope, RequestFactory) {

  $scope.strategyList = ["safe", "balanced", "aggressive"];

  $scope.id = 1;
  $scope.cash = 0;
  $scope.currentStrategy =  $scope.strategyList[0];
  $scope.funds = [];
  $scope.funds.push({id: $scope.id, type: "", name: ""});
  $scope.addItem = function(){
         $scope.id += 1;
         $scope.funds.push({ id: $scope.id, type: "", name: ""});
  };

  $scope.computeInvestment = function() {
     RequestFactory.saveData($scope.cash, $scope.currentStrategy, $scope.funds)
     .$promise.then( function(investment) {
        var resultCollection = [];
        var allResults = investment.results;
        for(result in allResults) {
            var fund = allResults[result].fund;
            var row = {
                id: fund.id,
                type: fund.type,
                name: fund.name,
                cash: allResults[result].cashValue,
                percent: allResults[result].percentValue
            }
            resultCollection.push(row);
        };
        resultCollection.sort(function(a, b) {
            return a.id - b.id;
        });
        $scope.remainCash = investment.remainCash;
        $scope.rowCollection = resultCollection.slice(0);
     });
  };

}])

.directive('showResults', function() {
     return {
        template: '<h2>Results</h2><br /><br />' +
            '<div id="resultsId" class="table-responsive">' +
                '<table id="tableId" st-table="rowCollection" class="table table-striped">' +
                    '<tr>' +
                        '<th>Id</th>' +
                        '<th>Type</th>' +
                        '<th>Name</th>' +
                        '<th>Cash</th>' +
                        '<th>Percent</th>' +
                    '</tr>' +
                    '<tr ng-repeat="row in rowCollection">' +
                        '<td>{{row.id}}</td>' +
                        '<td>{{row.type}}</td>' +
                        '<td>{{row.name}}</td>' +
                        '<td>{{row.cash}}</td>' +
                        '<td>{{row.percent}}</td>' +
                    '</tr>' +
                '</table>' +
            '</div>' +
            '<br />' +
            '<h3>Remain cash: {{remainCash}}'
      };
})

.factory('RequestFactory', ['$resource', function($resource) {
    var data = $resource('/investment/:id');

    this.saveData = function (cash, strategy, funds) {
        return data.save({"cash": cash, "strategyName": strategy, "funds": funds});
    }
    return this;
  }]
);
