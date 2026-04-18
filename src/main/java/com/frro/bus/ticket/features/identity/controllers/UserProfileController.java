// Delete, Update
//

// @PutMapping("/{id}")
// public ResponseEntity<UserResponse> update(@PathVariable int id, @RequestBody
// UserRequest userRequest) {
// Optional<UserResponse> updatedUser = userService.update(id, userRequest);
// return updatedUser.map(ResponseEntity::ok)
// .orElseGet(() -> ResponseEntity.notFound().build());
// }
//
//
// @DeleteMapping("/{id}")
// public ResponseEntity<UserResponse> delete(@PathVariable int id) {
// Optional<UserResponse> deletedUser = userService.delete(id);
// return deletedUser.map(ResponseEntity::ok)
// .orElseGet(() -> ResponseEntity.notFound().build());
// }
//
// Logical delete as well
