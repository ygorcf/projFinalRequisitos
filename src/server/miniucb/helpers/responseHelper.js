module.exports = function (success, status, message, data) {
    return {
        success: success,
        status: status,
        data: data,
        message: message
    }
}